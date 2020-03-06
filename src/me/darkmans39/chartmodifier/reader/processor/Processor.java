package me.darkmans39.chartmodifier.reader.processor;

import me.darkmans39.chartmodifier.reader.ChartReader;

public interface Processor {

    void process(String line, ChartReader appendTo);

}
