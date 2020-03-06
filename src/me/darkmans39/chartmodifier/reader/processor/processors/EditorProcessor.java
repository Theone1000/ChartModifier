package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.key.keys.EditorKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;

public final class EditorProcessor extends BulkProcessor {

    public EditorProcessor() {
        addGenericProcessorsOfType(String.class, EditorKeys.class, "editor");
        addGenericProcessorsOfType(Double.class, EditorKeys.class, "editor");
        addGenericProcessorsOfType(Integer.class, EditorKeys.class, "editor");
    }

    @Override
    public void process(String line, ChartReader appendTo) {

    }

    @Override
    public boolean isSemicolonSplitData() {
        return true;
    }

}
