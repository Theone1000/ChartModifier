package me.darkmans39.chartmodifier.chart.obj.key.keys.events;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class GenericEventKeys {

    private GenericEventKeys() {

    }

    public static final NamedKey<String> EVENT_TYPE = new NamedKey<>(String.class, "EventType");
    public static final NamedKey<Integer> START_TIME = new NamedKey<>(Integer.class, "StartTime");

}
