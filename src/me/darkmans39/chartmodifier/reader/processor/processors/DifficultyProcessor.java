package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;

public class DifficultyProcessor extends BulkProcessor {

    public DifficultyProcessor() {
        addGenericProcessorsOfType(Double.class, DifficultyKeys.class, "difficulty");
    }

    @Override
    public void process(String line, ChartReader appendTo) {

    }

    @Override
    public boolean isSemicolonSplitData() {
        return true;
    }

}
