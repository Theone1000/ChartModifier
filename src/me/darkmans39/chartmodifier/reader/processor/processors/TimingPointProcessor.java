package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.container.containers.TimingPoint;
import me.darkmans39.chartmodifier.chart.obj.key.keys.TimingPointKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class TimingPointProcessor extends BulkProcessor {

    @Override
    public void process(String line, ChartReader appendTo) {

        final String[] split = line.split(",", 8);

        if (split.length != 8) return;

        final TimingPoint point = new TimingPoint();

        point.setObject(TimingPointKeys.TIME, NumberUtil.parseInt(split[0], 0));
        point.setObject(TimingPointKeys.BEAT_LENGTH, NumberUtil.parseDouble(split[1], 0));
        point.setObject(TimingPointKeys.METER, NumberUtil.parseInt(split[2], 0));
        point.setObject(TimingPointKeys.SAMPLE_SET, NumberUtil.parseInt(split[3], 0));
        point.setObject(TimingPointKeys.SAMPLE_INDEX, NumberUtil.parseInt(split[4], 0));
        point.setObject(TimingPointKeys.VOLUME, NumberUtil.parseInt(split[5], 0));
        point.setObject(TimingPointKeys.UNINHERITED, NumberUtil.parseInt(split[6], 0));
        point.setObject(TimingPointKeys.EFFECTS, NumberUtil.parseInt(split[7], 0));

        appendTo.getChart().getTimingPoints().getObjects().add(point);
    }

    @Override
    public boolean isSemicolonSplitData() {
        return false;
    }

}
