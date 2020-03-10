package me.darkmans39.chartmodifier.application;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.application.ConsoleWriter.ConsoleWriteType;
import me.darkmans39.chartmodifier.application.chart.ChartDataTracker;
import me.darkmans39.chartmodifier.application.chart.ChartUprater;
import me.darkmans39.chartmodifier.application.chart.mods.ModFieldContainer;
import me.darkmans39.chartmodifier.application.data.StorageContainer;
import me.darkmans39.chartmodifier.application.osu.OsuStalker;
import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.GeneralKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class Application {

    private File lastLocation;
    private File directory;

    private final StorageContainer storage;
    private final OsuStalker stalker;
    private final ConsoleWriter writer;
    private final ChartUprater upRater;
    private final ChartDataTracker tracker;
    private final ModFieldContainer modFieldContainer;
    private final UI ui;

    public Application(UI ui) {
        this.ui = ui;

        try {
            this.directory = new File(URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.storage = new StorageContainer(this);
        this.modFieldContainer = new ModFieldContainer(this);
        this.stalker = new OsuStalker(this);
        this.writer = new ConsoleWriter(this);
        this.upRater = new ChartUprater(this);
        this.tracker = new ChartDataTracker(this);

        final DefaultTableModel model = (DefaultTableModel) ui.getChartTable().getModel();

        ui.getClearLoadCacheButton().addActionListener((e) -> {
            stalker.clearCache();
            writer.println("Cleared the Auto Chart Loader cache.", ConsoleWriteType.SUCCESS);
        });

        ui.getReloadChartFromHistoryButton().addActionListener((e) -> {

            final Chart chart = getSelectedChart(ui.getChartHistoryTable());

            if (chart != null) {
                writer.println("Loaded chart '" + chart + " from Chart History.", ConsoleWriteType.SUCCESS);
                ((DefaultTableModel) ui.getChartTable().getModel()).addRow(new Object[] { copyChart(chart) });
            }

        });

        ui.getOpenChartHistoryDirectoryButton().addActionListener((e) -> {
            openChartDirectory(getSelectedChart(ui.getChartHistoryTable()));
        });

        ui.getOpenChartDirectoryButton().addActionListener((e) -> {
            openChartDirectory(getSelectedChart(ui.getChartTable()));
        });

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
            getTracker().updateText(getSelectedChart(ui.getChartTable()));
        });

        ui.getRemoveDifficulty().addActionListener((e) -> {

            final Chart chart = getSelectedChart(ui.getChartTable());

            if (!removeChart(chart)) return;

            tracker.clearAllText();
            getTracker().resetSelection();
            getTracker().updateText(getSelectedChart(ui.getChartTable()));
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

                    getConsoleWriter().println("Duplicated chart '" + chart.toString() + "' and applied new rate '" + parsed + "x'", ConsoleWriteType.SUCCESS);
                }

            }

        });

        ui.getWriteAllDifficultiesButton().addActionListener((e) -> {

            if (ui.getUnloadAllChartsAfterWriteRadioButton().isSelected()) {

                while (ui.getChartTable().getRowCount() > 0) {
                    final Chart chart = (Chart) ui.getChartTable().getValueAt(0, 0);

                    upRater.submit(chart);
                    model.removeRow(0);
                }

                tracker.clearAllText();

            } else {
                for (int i = 0; i < ui.getChartTable().getRowCount(); i++) {
                    upRater.submit((Chart) ui.getChartTable().getValueAt(i, 0));
                }
            }

        });

        ui.getDuplicateButton().addActionListener((e) -> {

            final Chart chart = getSelectedChart(ui.getChartTable());

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
                        return "osu chart files (*.osu)";
                    }

                    @Override
                    public boolean accept(File f) {
                        return f.getName().endsWith(".osu") || f.isDirectory();
                    }
                });

                chooser.setMultiSelectionEnabled(true);

                int value = chooser.showOpenDialog(null);

                if (value != JFileChooser.APPROVE_OPTION) return;

                this.lastLocation = chooser.getCurrentDirectory();

                for (File file : chooser.getSelectedFiles()) {

                    final Chart chart = Chart.of(file);

                    if (chart.getGeneral().getObject(GeneralKeys.MODE) != 3) {
                        getConsoleWriter().println("Skipped non mania chart '" + chart.toString() + "'", ConsoleWriteType.ERROR);
                        continue;
                    }

                    model.addRow(new Object[] { chart });
                    ((DefaultTableModel) ui.getChartHistoryTable().getModel()).addRow(new Object[] { copyChart(chart) });

                    getConsoleWriter().println("Loaded chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
                }

            });

        });

        ui.getChartTable().getSelectionModel().addListSelectionListener((e) -> {
            if (e.getValueIsAdjusting()) return;

            final Chart last = getLastChart();

            if (last != null) getModFieldContainer().applyAllFields(last);

            getTracker().updateText(getSelectedChart(ui.getChartTable()));
        });

        getUI().getUpdateOsuDirectoryButton().addActionListener((e) -> {

            SwingUtilities.invokeLater(() -> {
                final JFileChooser chooser = new JFileChooser();
                final File last = getStorageContainer().getStorage().getOsuDirectory();

                if (last != null) chooser.setCurrentDirectory(last);

                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setFileFilter(new FileFilter() {

                    @Override
                    public String getDescription() {
                        return "Directories";
                    }

                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory();
                    }
                });

                final int value = chooser.showOpenDialog(null);

                if (value != JFileChooser.APPROVE_OPTION) return;

                final File file = chooser.getSelectedFile();

                getStorageContainer().getStorage().setOsuDirectory(file);
                getStorageContainer().updateOsuDirectoryText();
            });

        });

        getStorageContainer().updateOsuDirectoryText();
    }

    public File getDirectory() {
        return directory;
    }

    private Chart lastChart;

    public Chart getLastChart() {
        return lastChart;
    }

    public Chart getSelectedChart(JTable table) {

        int row = table.getSelectedRow();

        if (row == -1) return null;

        final Chart chart = (Chart) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());

        if (table == ui.getChartTable()) lastChart = chart;

        return chart;
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

        getConsoleWriter().println("Unloaded chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
        return true;
    }

    public void openChartDirectory(Chart chart) {

        if (chart == null || !chart.getFile().exists()) return;

        try {
            Runtime.getRuntime().exec("explorer.exe /select," + chart.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ChartDataTracker getTracker() {
        return tracker;
    }

    public ConsoleWriter getConsoleWriter() {
        return writer;
    }

    public OsuStalker getStalker() {
        return stalker;
    }

    public StorageContainer getStorageContainer() {
        return storage;
    }

    public UI getUI() {
        return ui;
    }

    public ModFieldContainer getModFieldContainer() {
        return modFieldContainer;
    }

}
