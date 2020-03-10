package me.darkmans39.chartmodifier.chart.obj.list.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.OsuString;
import me.darkmans39.chartmodifier.chart.obj.container.containers.events.Break;
import me.darkmans39.chartmodifier.chart.obj.list.ListContainer;

public final class Breaks extends ListContainer<Break, List<Break>> {

    public Breaks() {
        super("//Break Periods", false, new ArrayList<>());
    }

    @Override
    public String toOsuString() {

        final StringJoiner joiner = new StringJoiner("\n");

        joiner.add("//Break Periods");

        for (OsuString obj : getObjects()) {
            joiner.add(obj.toOsuString());
        }

        joiner.add("//Storyboard Layer 0 (Background)");
        joiner.add("//Storyboard Layer 1 (Fail)");
        joiner.add("//Storyboard Layer 2 (Pass)");
        joiner.add("//Storyboard Layer 3 (Foreground)");
        joiner.add("//Storyboard Sound Samples");

        return joiner.toString() + "\n";
    }

}
