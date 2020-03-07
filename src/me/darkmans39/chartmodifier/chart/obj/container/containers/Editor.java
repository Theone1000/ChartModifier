package me.darkmans39.chartmodifier.chart.obj.container.containers;

import me.darkmans39.chartmodifier.chart.obj.container.Container;

public final class Editor extends Container<Editor> {

    public Editor() {
        super("Editor", true);
    }

    @Override
    public Editor getThis() {
        return this;
    }

}
