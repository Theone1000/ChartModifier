package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.container.containers.HitObject;
import me.darkmans39.chartmodifier.chart.obj.key.keys.HitObjectKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class HitObjectProcessor extends BulkProcessor {

    @Override
    public void process(String line, ChartReader appendTo) {

        final String[] baseSplit = line.split(",", 7);

        if (baseSplit.length < 5) return;

        final HitObject obj = new HitObject();

        obj.setObject(HitObjectKeys.X, NumberUtil.parseInt(baseSplit[0], 0));
        obj.setObject(HitObjectKeys.Y, NumberUtil.parseInt(baseSplit[1], 0));
        obj.setObject(HitObjectKeys.TIME, NumberUtil.parseInt(baseSplit[2], 0));
        obj.setObject(HitObjectKeys.TYPE, NumberUtil.parseInt(baseSplit[3], 0));
        obj.setObject(HitObjectKeys.HIT_SOUND, NumberUtil.parseInt(baseSplit[4], 0));
        if (baseSplit.length > 5) obj.setObject(HitObjectKeys.OBJECT_PARAMS, baseSplit[5]);

        if (baseSplit.length == 7) obj.setObject(HitObjectKeys.HIT_SOUND, NumberUtil.parseInt(baseSplit[6], 0));

        appendTo.getChart().getHitObjects().getObjects().add(obj);
    }

    @Override
    public boolean isSemicolonSplitData() {
        return false;
    }

}
