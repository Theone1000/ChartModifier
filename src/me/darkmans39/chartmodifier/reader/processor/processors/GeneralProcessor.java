package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.key.keys.GeneralKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;

public final class GeneralProcessor extends BulkProcessor {

    public GeneralProcessor() {
        addGenericProcessorsOfType(String.class, GeneralKeys.class, "general");
        addGenericProcessorsOfType(Integer.class, GeneralKeys.class, "general");
        addGenericProcessorsOfType(Float.class, GeneralKeys.class, "general");
    }

    @Override
    public void process(String line, ChartReader appendTo) {

    }

    @Override
    public boolean isSemicolonSplitData() {
        return true;
    }

}
