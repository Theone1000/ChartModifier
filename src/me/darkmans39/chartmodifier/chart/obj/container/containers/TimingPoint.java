package me.darkmans39.chartmodifier.chart.obj.container.containers;

import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.keys.TimingPointKeys;

public final class TimingPoint extends Container<TimingPoint> {

    public TimingPoint() {
        super(null);
    }

    @Override
    public TimingPoint getThis() {
        return this;
    }

    @Override
    public String toOsuString() {

        final StringJoiner joiner = new StringJoiner(",");

        joiner.add(getObject(TimingPointKeys.TIME).toString());
        joiner.add(getObject(TimingPointKeys.BEAT_LENGTH).toString());
        joiner.add(getObject(TimingPointKeys.METER).toString());
        joiner.add(getObject(TimingPointKeys.SAMPLE_SET).toString());
        joiner.add(getObject(TimingPointKeys.SAMPLE_INDEX).toString());
        joiner.add(getObject(TimingPointKeys.VOLUME).toString());
        joiner.add(getObject(TimingPointKeys.UNINHERITED).toString());
        joiner.add(getObject(TimingPointKeys.EFFECTS).toString());

        return joiner.toString();
    }

}
