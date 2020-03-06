package me.darkmans39.chartmodifier.chart.obj.container.containers;

import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.keys.HitObjectKeys;

public final class HitObject extends Container<HitObject> {

    public HitObject() {
        super(null);
    }

    @Override
    public HitObject getThis() {
        return this;
    }

    @Override
    public String toOsuString() {

        final StringJoiner joiner = new StringJoiner(",");

        joiner.add(getObject(HitObjectKeys.X).toString());
        joiner.add(getObject(HitObjectKeys.Y).toString());
        joiner.add(getObject(HitObjectKeys.TIME).toString());
        joiner.add(getObject(HitObjectKeys.TYPE).toString());
        joiner.add(getObject(HitObjectKeys.HIT_SOUND).toString());
        joiner.add(getObject(HitObjectKeys.OBJECT_PARAMS));

        final Integer sample = getObject(HitObjectKeys.HIT_SAMPLE);

        if (sample != null) joiner.add(sample.toString());

        return joiner.toString();
    }

}
