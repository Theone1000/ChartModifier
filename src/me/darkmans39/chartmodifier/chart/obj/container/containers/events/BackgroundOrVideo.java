package me.darkmans39.chartmodifier.chart.obj.container.containers.events;

import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.key.keys.events.BackgroundAndVideoKeys;
import me.darkmans39.chartmodifier.util.StringUtil;

public final class BackgroundOrVideo extends EventContainer<BackgroundOrVideo> {

    @Override
    public BackgroundOrVideo getThis() {
        return this;
    }

    @Override
    public String toOsuString() {

        final StringJoiner joiner = new StringJoiner(",");

        joiner.add(super.toOsuString());
        joiner.add(getObject(BackgroundAndVideoKeys.FILE_NAME));

        StringUtil.appendNonNull(joiner, getObject(BackgroundAndVideoKeys.X_OFFSET));
        StringUtil.appendNonNull(joiner, getObject(BackgroundAndVideoKeys.Y_OFFSET));

        return joiner.toString();
    }

}
