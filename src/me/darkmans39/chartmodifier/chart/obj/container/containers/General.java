package me.darkmans39.chartmodifier.chart.obj.container.containers;

import me.darkmans39.chartmodifier.chart.obj.container.Container;

public final class General extends Container<General> {

    public General() {
        super("General",true);
    }

    @Override
    public General getThis() {
        return this;
    }

}
