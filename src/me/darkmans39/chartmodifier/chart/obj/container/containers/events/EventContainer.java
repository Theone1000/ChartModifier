package me.darkmans39.chartmodifier.chart.obj.container.containers.events;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.GenericEventKeys;

public abstract class EventContainer<S> extends Container<S> {

    public EventContainer() {
        super(null);
    }

    @Override
    public String toOsuString() {
        return getObject(GenericEventKeys.EVENT_TYPE) + "," + getObject(GenericEventKeys.START_TIME);
    }

}
