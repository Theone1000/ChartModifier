package me.darkmans39.chartmodifier.chart.obj.container.containers;

import me.darkmans39.chartmodifier.chart.obj.container.Container;

public final class Difficulty extends Container<Difficulty> {

    public Difficulty() {
        super("Difficulty");
    }

    @Override
    public Difficulty getThis() {
        return this;
    }

}
