package me.darkmans39.chartmodifier.application.chart;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.application.Application;
import me.darkmans39.chartmodifier.application.ConsoleWriter.ConsoleWriteType;
import me.darkmans39.chartmodifier.audio.AudioSpeedup;
import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.GeneralKeys;
import me.darkmans39.chartmodifier.util.StringUtil;

public final class ChartUprater {

    private final Object lock = new Object();

    private final ExecutorService executor;
    private final Application application;

    public ChartUprater(Application application) {
        this.application = application;
        this.executor = Executors.newCachedThreadPool();
    }

    public void submit(Chart chart) {

        application.getConsoleWriter().println("Starting write for '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);

        final JTable table = application.getUI().getWritingDifficultiesTable();

        final DefaultTableModel model = (DefaultTableModel) table.getModel();

        application.getModFieldContainer().applyAllFields(chart);

        synchronized (lock) {
            model.addRow(new Object[] { chart });
        }

        executor.execute(() -> {

            final File file = new File(chart.getFile().getParentFile(), chart.getGeneral().getObject(GeneralKeys.AUDIO_FILE_NAME));

            if (!file.exists()) {
                application.getConsoleWriter().println("Audio file doesn't exist for '" + chart.toString() + "'", ConsoleWriteType.ERROR);
                SwingUtilities.invokeLater(() -> removeLinear(model, chart));
                return;
            }

            final File audioFile;

            if (chart.getCachedRate() != 1) {

                try {
                    System.out.println("Audio DIR: " + application.getDirectory());
                    audioFile = AudioSpeedup.speedUp(application.getDirectory(), file, chart.getCachedRate(), application.getUI().getKeepPitchRadioButton().isSelected());
                } catch (Exception e) {
                    application.getConsoleWriter().println("Unable to speed up audio file: " + e, ConsoleWriteType.ERROR);
                    SwingUtilities.invokeLater(() -> removeLinear(model, chart));
                    return;
                }

                chart.uprate(chart.getCachedRate());
            } else {
                audioFile = file;
            }

            chart.getGeneral().setObject(GeneralKeys.AUDIO_FILE_NAME, audioFile.getName());

            int rand = ThreadLocalRandom.current().nextInt(100000);

            final File toWrite = new File(chart.getFile().getParentFile(), rand + " - " + StringUtil.withoutExtension(chart.getFile()) + " (" + chart.getCachedRate() + "x).osu");

            chart.write(toWrite);
            application.getConsoleWriter().println("Wrote chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
            SwingUtilities.invokeLater(() -> removeLinear(model, chart));
        });

    }

    public void removeLinear(DefaultTableModel model, Chart lookFor) {

        synchronized (lock) {
            final int index = linearSearch(model, lookFor);

            if (index != -1) model.removeRow(index);
        }

    }

    public static int linearSearch(DefaultTableModel model, Chart lookFor) {

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0) == lookFor) return i;
        }

        return -1;
    }

}
