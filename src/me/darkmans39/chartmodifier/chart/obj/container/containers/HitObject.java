package me.darkmans39.chartmodifier.chart.obj.container.containers;

import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.keys.HitObjectKeys;
import me.darkmans39.chartmodifier.util.StringUtil;

public final class HitObject extends Container<HitObject> implements Comparable<HitObject> {

    public HitObject() {
        super(null, false);
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
        StringUtil.appendNonNull(joiner, getObject(HitObjectKeys.OBJECT_PARAMS));
        StringUtil.appendNonNull(joiner, getObject(HitObjectKeys.HIT_SAMPLE));

        return joiner.toString();
    }

    @Override
    public int compareTo(HitObject o) {
        return getObject(HitObjectKeys.TIME) - o.getObject(HitObjectKeys.TIME);
    }

}
