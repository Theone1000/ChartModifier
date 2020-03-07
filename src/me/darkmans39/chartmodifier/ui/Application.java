package me.darkmans39.chartmodifier.ui;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.ui.ConsoleWriter.ConsoleWriteType;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class Application {

    private File lastLocation;
    private File directory;

    private final ConsoleWriter writer;
    private final ChartUprater upRater;
    private final ChartDataTracker tracker;
    private final UI ui;

    public Application(UI ui) {
        this.ui = ui;
        this.directory = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        this.writer = new ConsoleWriter(this);
        this.upRater = new ChartUprater(this);
        this.tracker = new ChartDataTracker(this);

        final DefaultTableModel model = (DefaultTableModel) ui.getChartTable().getModel();

        ui.getConsoleClearButton().addActionListener((e) -> {
            getConsoleWriter().clear();
        });

        ui.getRemoveAllSelectedChartsButton().addActionListener((e) -> {

            final List<Chart> selectedCharts = getSelectedCharts();

            if (selectedCharts.isEmpty()) return;

            for (Chart chart : selectedCharts) {
                removeChart(chart);
            }

            getTracker().clearAllText();
            getTracker().resetSelection();
            getTracker().updateText(getSelectedChart());

        });

        ui.getRemoveDifficulty().addActionListener((e) -> {

            final Chart chart = getSelectedChart();

            if (!removeChart(chart)) return;

            tracker.clearAllText();
            getTracker().resetSelection();
            getTracker().updateText(getSelectedChart());
        });

        ui.getMassApplyRatesButton().addActionListener((e) -> {

            final List<Chart> selectedCharts = getSelectedCharts();

            if (selectedCharts.isEmpty()) return;

            final String[] split = ui.getMassApplyRatesTextField().getText().trim().split(" ");

            if (split[0].isEmpty()) return;

            for (String rate : split) {

                final Double parsed = NumberUtil.parseDouble(rate, -1);

                if (parsed <= 0) continue;

                for (Chart chart : selectedCharts) {

                    final Chart copy = copyChart(chart);

                    copy.setCachedRate(parsed);
                    copy.getMetadata().setObject(MetadataKeys.VERSION, copy.getMetadata().getObject(MetadataKeys.VERSION) + " " + parsed + "x");

                    model.addRow(new Object[] { copy });
                }

            }

        });

        ui.getWriteAllDifficultiesButton().addActionListener((e) -> {

            while (ui.getChartTable().getRowCount() > 0) {
                final Chart chart = (Chart) ui.getChartTable().getValueAt(0, 0);

                upRater.submit(chart);
                model.removeRow(0);
            }

            tracker.clearAllText();

        });

        ui.getDuplicateButton().addActionListener((e) -> {

            final Chart chart = getSelectedChart();

            if (chart == null) return;

            model.addRow(new Object[] { copyChart(chart) });

            getConsoleWriter().println("Duplicated chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
        });

        ui.getLoadFromFileButton().addActionListener((e) -> {

            EventQueue.invokeLater(() -> {
                final JFileChooser chooser = new JFileChooser();

                if (lastLocation != null) chooser.setCurrentDirectory(lastLocation);

                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileFilter() {

                    @Override
                    public String getDescription() {
                        return ".osu files";
                    }

                    @Override
                    public boolean accept(File f) {
                        return f.getName().endsWith(".osu") || f.isDirectory();
                    }
                });

                chooser.setMultiSelectionEnabled(true);
                chooser.showOpenDialog(null);

                this.lastLocation = chooser.getCurrentDirectory();

                for (File file : chooser.getSelectedFiles()) {

                    final Chart chart = Chart.of(file);

                    model.addRow(new Object[] { chart });

                    getConsoleWriter().println("Loaded chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
                }

            });

        });

        ui.getChartTable().getSelectionModel().addListSelectionListener((e) -> {
            if (e.getValueIsAdjusting()) return;
            getTracker().updateText(getSelectedChart());
        });
    }

    public File getDirectory() {
        return directory;
    }

    public Chart getSelectedChart() {

        int row = ui.getChartTable().getSelectedRow();

        if (row == -1) return null;

        return (Chart) ui.getChartTable().getModel().getValueAt(ui.getChartTable().getSelectedRow(), ui.getChartTable().getSelectedColumn());
    }

    public List<Chart> getSelectedCharts() {

        final List<Chart> charts = new ArrayList<>();
        final JTable table = ui.getChartTable();

        for (int row : table.getSelectedRows()) {

            final Chart chart = (Chart) table.getModel().getValueAt(row, 0);

            if (chart != null) charts.add(chart);

        }

        return charts;
    }

    public Chart copyChart(Chart chart) {

        if (chart == null) return null;

        final Chart reread = Chart.of(chart.getFile());

        reread.setCachedRate(chart.getCachedRate());

        reread.getMetadata().setObject(MetadataKeys.TITLE, chart.getMetadata().getObject(MetadataKeys.TITLE));
        reread.getMetadata().setObject(MetadataKeys.VERSION, chart.getMetadata().getObject(MetadataKeys.VERSION));

        reread.getDifficulty().setObject(DifficultyKeys.HP_DRAIN_RATE, chart.getDifficulty().getObject(DifficultyKeys.HP_DRAIN_RATE));
        reread.getDifficulty().setObject(DifficultyKeys.OVERALL_DIFFICULTY, chart.getDifficulty().getObject(DifficultyKeys.OVERALL_DIFFICULTY));

        return reread;
    }

    public boolean removeChart(Chart chart) {

        if (chart == null) return false;

        final DefaultTableModel model = (DefaultTableModel) ui.getChartTable().getModel();
        final int index = ChartUprater.linearSearch(model, chart);

        if (index != -1) model.removeRow(index);

        getConsoleWriter().println("Removed chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
        return true;
    }

    public ChartDataTracker getTracker() {
        return tracker;
    }

    public ConsoleWriter getConsoleWriter() {
        return writer;
    }

    public UI getUI() {
        return ui;
    }

}
