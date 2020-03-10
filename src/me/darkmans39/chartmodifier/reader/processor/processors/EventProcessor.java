package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.container.containers.events.BackgroundOrVideo;
import me.darkmans39.chartmodifier.chart.obj.container.containers.events.Break;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.BackgroundAndVideoKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.BreakKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.EventKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.GenericEventKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;
import me.darkmans39.chartmodifier.reader.processor.Processor;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class EventProcessor extends BulkProcessor {

    private Processor currentProcessor;

    public EventProcessor() {

        addProcessor("//Background and Video events", (line, appendTo) -> {

            final String[] split = line.split(",");

            final BackgroundOrVideo bg = populateGeneric(new BackgroundOrVideo(), split);

            bg.setObject(BackgroundAndVideoKeys.FILE_NAME, split[2]);

            if (split.length >= 5) populateGenericOffsets(bg, split);

            appendTo.getChart().getEvents().getObject(EventKeys.BACKGROUNDS_OR_VIDEOS).getObjects().add(bg);
        });

        addProcessor("//Break Periods", (line, appendTo) -> {

            final String[] split = line.split(",", 3);
            final Break breakObj = populateGeneric(new Break(), split);

            if (split.length > 2) breakObj.setObject(BreakKeys.END_TIME, NumberUtil.parseInt(split[2], 0));

            appendTo.getChart().getEvents().getObject(EventKeys.BREAKS).getObjects().add(breakObj);
        });

    }

    private static <T extends Container<T>> T populateGeneric(T toPopulate, String[] input) {
        toPopulate.setObject(GenericEventKeys.EVENT_TYPE, input[0]);
        if (input.length > 2) toPopulate.setObject(GenericEventKeys.START_TIME, NumberUtil.parseInt(input[1], 0));
        return toPopulate;
    }

    private static <T extends Container<T>> T populateGenericOffsets(T toPopulate, String[] input) {
        toPopulate.setObject(BackgroundAndVideoKeys.X_OFFSET, NumberUtil.parseInt(input[3], 0));
        toPopulate.setObject(BackgroundAndVideoKeys.Y_OFFSET, NumberUtil.parseInt(input[4], 0));
        return toPopulate;
    }

    @Override
    public void onStart() {
        currentProcessor = null;
    }

    @Override
    public void process(String line, ChartReader appendTo) {

        if (line.startsWith("//")) {
            currentProcessor = getProcessor(line);
            return;
        }

        if (currentProcessor == null) return;

        currentProcessor.process(line, appendTo);
    }

    @Override
    public boolean isSemicolonSplitData() {
        return false;
    }
}
