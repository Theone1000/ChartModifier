package me.darkmans39.chartmodifier.chart.obj.list.lists;

import java.util.ArrayList;
import java.util.List;

import me.darkmans39.chartmodifier.chart.obj.container.containers.events.BackgroundOrVideo;
import me.darkmans39.chartmodifier.chart.obj.list.ListContainer;

public final class BackgroundsAndVideos extends ListContainer<BackgroundOrVideo, List<BackgroundOrVideo>> {

    public BackgroundsAndVideos() {
        super("//Background and Video events", false, new ArrayList<>());
    }

}
