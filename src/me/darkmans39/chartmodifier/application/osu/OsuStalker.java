package me.darkmans39.chartmodifier.application.osu;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.application.Application;
import me.darkmans39.chartmodifier.application.ConsoleWriter.ConsoleWriteType;
import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.GeneralKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.util.WindowsUtil;

public final class OsuStalker extends Thread {

    private final Object lock;
    private final ExecutorService executor;
    private final Application application;
    private final Set<String> parsedCharts;

    public OsuStalker(Application application) {
        this.lock = new Object();
        this.executor = Executors.newCachedThreadPool();
        this.application = application;
        this.parsedCharts = new HashSet<>();
        start();
    }

    public File getSongFolder() {
        File file = application.getStorageContainer().getStorage().getOsuDirectory();

        if (file == null) return null;

        File songFolder = new File(file, "songs");

        return !songFolder.exists() ? null : songFolder;
    }

    public void clearCache() {
        synchronized (lock) {
            parsedCharts.clear();
        }
    }

    public boolean addChart(String name) {
        synchronized (lock) {
            return parsedCharts.add(name);
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!application.getUI().getAutoScanChartsRadioButton().isSelected()) continue;

            final List<String> names = WindowsUtil.getAllWindowNames();

            for (String name : names) {

                if (!name.startsWith("osu!") || name.endsWith(".osu")) continue;

                name = name.trim().replaceAll(" +", " ");

                final String[] split = name.split(" - ", 3);

                if (!split[0].equals("osu!") || split.length == 1) continue;

                final File file = getSongFolder();

                if (file == null || !file.isDirectory()) continue;

                if (addChart(name)) {

                    System.out.println("Queuing");

                    executor.submit(() -> {

                        try {

                            final String artist = split[1];
                            final String unparsed = split[split.length - 1];
                            final int diffSepIndex = unparsed.indexOf(" [");

                            final String title = unparsed.substring(0, diffSepIndex).trim();
                            final String version = unparsed.substring(diffSepIndex + 2, unparsed.length() - 1).trim();

                            String check = (artist == null ? title : artist + " - " + title).trim();

                            check = check.replaceAll("[!@#$%^&*()_.,]|\\~|\\-", "");

                            final File[] parentFiles = file.listFiles();

                            final Set<String> temp = new HashSet<>();

                            if (parentFiles == null) return;

                            for (File parent : parentFiles) {

                                String fileName = parent.getName().replaceAll("[!@#$%^&*()_.,]|\\~|\\-", "").trim();

                                if (fileName.contains(check) || fileName.contains(version)) {

                                    final File[] childFiles = parent.listFiles();

                                    if (childFiles == null) continue;

                                    for (File child : parent.listFiles()) {

                                        if (!child.getName().endsWith(".osu")) continue;

                                        final Chart chart = Chart.of(child);
                                        final String chartVersion = chart.getMetadata().getObject(MetadataKeys.VERSION);

                                        if (!temp.add(chartVersion)) continue;

                                        if (chart.getGeneral().getObjectOrDef(GeneralKeys.MODE, -1) != 3) continue;
                                        if (!chartVersion.equals(version)) continue;

                                        SwingUtilities.invokeLater(() -> {
                                            ((DefaultTableModel) application.getUI().getChartTable().getModel()).addRow(new Object[] { chart });
                                            ((DefaultTableModel) application.getUI().getChartHistoryTable().getModel()).addRow(new Object[] { chart });
                                        });

                                        application.getConsoleWriter().println("Auto loaded chart '" + chart.toString() + "'", ConsoleWriteType.SUCCESS);
                                        break;
                                    }

                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            application.getConsoleWriter().println("Error when auto loading chart: " + e, ConsoleWriteType.ERROR);
                        }

                    });

                }

            }

        }

    }

}
