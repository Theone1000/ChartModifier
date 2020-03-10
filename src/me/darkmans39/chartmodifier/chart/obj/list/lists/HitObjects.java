package me.darkmans39.chartmodifier.chart.obj.list.lists;

import java.util.ArrayList;
import java.util.List;

import me.darkmans39.chartmodifier.chart.obj.container.containers.HitObject;
import me.darkmans39.chartmodifier.chart.obj.list.ListContainer;

public final class HitObjects extends ListContainer<HitObject, List<HitObject>> {

    public HitObjects() {
        super("HitObjects", true, new ArrayList<>());
    }

}
