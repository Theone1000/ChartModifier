package me.darkmans39.chartmodifier.chart.obj.list.lists;

import java.util.ArrayList;
import java.util.List;

import me.darkmans39.chartmodifier.chart.obj.container.containers.TimingPoint;
import me.darkmans39.chartmodifier.chart.obj.list.ListContainer;

public final class TimingPoints extends ListContainer<TimingPoint, List<TimingPoint>> {

    public TimingPoints() {
        super("TimingPoints", true, new ArrayList<>());
    }

}
