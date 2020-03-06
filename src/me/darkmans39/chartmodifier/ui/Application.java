package me.darkmans39.chartmodifier.ui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;

public final class Application {

    private File lastLocation;

    private ChartDataTracker tracker;
    private final UI ui;

    public Application(UI ui) {
        this.ui = ui;

        final DefaultTableModel model = (DefaultTableModel) ui.getChartTable().getModel();

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

                }

            });

        });

        ui.getChartTable().getSelectionModel().addListSelectionListener((e) -> {
            if (e.getValueIsAdjusting()) return;

            final Chart chart = getSelectedChart();

            if (chart == null) return;

            ui.getChartNameTextField().setText(chart.getMetadata().getObject(MetadataKeys.TITLE));
            ui.getChartDifficultyTextField().setText(chart.getMetadata().getObject(MetadataKeys.VERSION));
            ui.getRatesTextField().setText(Double.toString(chart.getCachedRate()));
            ui.getHpTextField().setText(chart.getDifficulty().getObject(DifficultyKeys.HP_DRAIN_RATE).toString());
            ui.getODTextField().setText(chart.getDifficulty().getObject(DifficultyKeys.OVERALL_DIFFICULTY).toString());
        });

        this.tracker = new ChartDataTracker(this);
    }

    public Chart getSelectedChart() {

        int row = ui.getChartTable().getSelectedRow();

        if (row == -1) return null;

        return (Chart) ui.getChartTable().getModel().getValueAt(ui.getChartTable().getSelectedRow(), ui.getChartTable().getSelectedColumn());
    }

    public UI getUI() {
        return ui;
    }

}
