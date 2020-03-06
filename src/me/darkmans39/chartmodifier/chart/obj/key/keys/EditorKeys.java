package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class EditorKeys {

    private EditorKeys() {

    }

    public static NamedKey<String> BOOKMARKS = new NamedKey<>(String.class, "Bookmarks");

    public static NamedKey<Double> DISTANCE_SPACING = new NamedKey<>(Double.class, "DistanceSpacing");
    public static NamedKey<Double> BEAT_DIVISOR = new NamedKey<>(Double.class, "BeatDivisor");
    public static NamedKey<Double> TIMELINE_ZOOM = new NamedKey<>(Double.class, "TimelineZoom");

    public static NamedKey<Integer> GRID_SIZE = new NamedKey<>(Integer.class, "GridSize");

}
