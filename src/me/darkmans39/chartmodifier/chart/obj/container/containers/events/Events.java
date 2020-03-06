package me.darkmans39.chartmodifier.chart.obj.container.containers.events;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.EventKeys;
import me.darkmans39.chartmodifier.chart.obj.list.lists.BackgroundsAndVideos;
import me.darkmans39.chartmodifier.chart.obj.list.lists.Breaks;

public final class Events extends Container<Events> {

    public Events() {
        super("Events");

        setObject(EventKeys.BACKGROUNDS_OR_VIDEOS, new BackgroundsAndVideos());
        setObject(EventKeys.BREAKS, new Breaks());
    }

    @Override
    public Events getThis() {
        return this;
    }

    @Override
    public String toOsuString() {

        final StringBuilder builder = new StringBuilder();

        builder.append("[" + getIdentifier() + "]").append("\n");
        builder.append(getObject(EventKeys.BACKGROUNDS_OR_VIDEOS).toOsuString());
        builder.append(getObject(EventKeys.BREAKS).toOsuString());
        return builder.toString();
    }

}
