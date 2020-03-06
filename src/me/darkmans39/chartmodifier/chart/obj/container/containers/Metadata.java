package me.darkmans39.chartmodifier.chart.obj.container.containers;

import me.darkmans39.chartmodifier.chart.obj.container.Container;

public class Metadata extends Container<Metadata> {

    public Metadata() {
        super("Metadata");
    }

    @Override
    public Metadata getThis() {
        return this;
    }

}
