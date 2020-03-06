package me.darkmans39.chartmodifier.chart.obj.container.containers.events;

import me.darkmans39.chartmodifier.chart.obj.key.keys.events.BreakKeys;

public class Break extends EventContainer<Break> {

    @Override
    public Break getThis() {
        return null;
    }

    @Override
    public String toOsuString() {
        return super.toOsuString() + "," + getObject(BreakKeys.END_TIME);
    }

}
