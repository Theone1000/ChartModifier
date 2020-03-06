package me.darkmans39.chartmodifier.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;
import me.darkmans39.chartmodifier.reader.processor.Processor;
import me.darkmans39.chartmodifier.reader.processor.processors.DifficultyProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.EditorProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.EventProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.GeneralProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.HitObjectProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.MetadataProcessor;
import me.darkmans39.chartmodifier.reader.processor.processors.TimingPointProcessor;

public final class ChartReader {

    private static final Map<String, BulkProcessor> processors;

    static {
        processors = new HashMap<>();

        add("[General]", new GeneralProcessor());
        add("[Editor]", new EditorProcessor());
        add("[Metadata]", new MetadataProcessor());
        add("[Difficulty]", new DifficultyProcessor());
        add("[HitObjects]", new HitObjectProcessor());
        add("[TimingPoints]", new TimingPointProcessor());
        add("[Events]", new EventProcessor());
    }

    public static void add(String identifier, BulkProcessor processor) {
        processors.put(identifier, processor);
    }

    public static void readTo(File read, Chart to) {

        if (!read.exists()) throw new IllegalArgumentException("File is not existent.");
        if (!read.getName().endsWith(".osu")) throw new IllegalArgumentException("File must be a .osu file");

        new ChartReader(read, to);
    }

    /*
     *
     */

    private final File read;
    private final Chart chart;

    private ChartReader(File read, Chart to) {
        this.chart = to;
        this.read = read;

        read();
    }

    private void read() {

        final List<String> lines;

        try {
            lines = Files.readAllLines(read.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        BulkProcessor current = null;

        /*
         * Set file format version
         */

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i);

            if (line.startsWith("[")) {
                current = processors.get(line);

                if (current != null) current.onStart();
                continue;
            }

            if (current == null) continue;

            current.process(line, this);

            if (current.isSemicolonSplitData()) {

                final String[] split = line.split(":", 2);

                if (split.length == 1) continue;

                final Processor processor = current.getProcessor(split[0]);

                if (processor != null) processor.process(split[1].trim(), this);

            }

        }

    }

    public Chart getChart() {
        return chart;
    }

}
