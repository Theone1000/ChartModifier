package me.darkmans39.chartmodifier.chart.obj.key.keys.events;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;
import me.darkmans39.chartmodifier.chart.obj.list.lists.BackgroundsAndVideos;
import me.darkmans39.chartmodifier.chart.obj.list.lists.Breaks;

public final class EventKeys {

    private EventKeys() {

    }

    public static final NamedKey<BackgroundsAndVideos> BACKGROUNDS_OR_VIDEOS = new NamedKey<>(BackgroundsAndVideos.class, "Backgrounds");
    public static final NamedKey<Breaks> BREAKS = new NamedKey<>(Breaks.class, "Breaks");
}
