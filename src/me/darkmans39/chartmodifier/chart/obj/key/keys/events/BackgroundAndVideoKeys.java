package me.darkmans39.chartmodifier.chart.obj.key.keys.events;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class BackgroundAndVideoKeys {

    private BackgroundAndVideoKeys() {

    }

    public static final NamedKey<String> FILE_NAME = new NamedKey<>(String.class, "FileName");
    public static final NamedKey<Integer> START_TIME = new NamedKey<>(Integer.class, "StartTime");
    public static final NamedKey<Integer> X_OFFSET = new NamedKey<>(Integer.class, "XOffset");
    public static final NamedKey<Integer> Y_OFFSET = new NamedKey<>(Integer.class, "YOffset");
}
