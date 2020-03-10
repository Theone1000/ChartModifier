package me.darkmans39.chartmodifier.chart.mods.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.mods.Mod;
import me.darkmans39.chartmodifier.chart.mods.ModData;
import me.darkmans39.chartmodifier.chart.obj.container.containers.HitObject;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.HitObjectKeys;
import me.darkmans39.chartmodifier.util.KeyModeColumnPositions;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class QuadMod implements Mod {

    @Override
    public void apply(Chart chart, ModData data) {

        final int density = NumberUtil.parseInt(data.getObject("density"), 3);
        final int keyCount = chart.getDifficulty().getObject(DifficultyKeys.CIRCLE_SIZE).intValue();

        int cur = 0;

        for (HitObject obj : new ArrayList<>(chart.getHitObjects().getObjects())) {
            cur++;

            if (cur % density != 0) continue;

            placeQuad(keyCount, chart, obj);
        }

    }

    private void placeQuad(int keyCount, Chart chart, HitObject base) {

        final int baseTime = base.getObject(HitObjectKeys.TIME);

        final Set<Integer> toNotPlace = new HashSet<>();

        for (HitObject obj : chart.getHitObjects().getObjects()) {

            final int current = obj.getObject(HitObjectKeys.TIME);

            if (current > baseTime + 50) break;

            if (current >= baseTime && current <= baseTime + 50) {
                toNotPlace.add(obj.getObject(HitObjectKeys.X));
            }

        }

        final KeyModeColumnPositions positions = KeyModeColumnPositions.getFromKeyMode(keyCount);

        outer: for (int i = 0; i < keyCount; i++) {
            final int pos = positions.getColumnPosition(i);

            if (toNotPlace.contains(pos)) continue;

            for (HitObject obj : chart.getHitObjects().getObjects()) {

                if (obj.getObject(HitObjectKeys.X) != pos) continue;

                final int startTime = obj.getObject(HitObjectKeys.TIME);

                if (startTime > baseTime) continue;

                final String params = obj.getObject(HitObjectKeys.OBJECT_PARAMS);

                if (params == null) continue;

                if (!params.split(":")[0].equals("0") && !params.contains("|")) {

                    final int endTime = NumberUtil.parseInt(params.substring(0, params.indexOf(':')), 0);

                    if (baseTime >= startTime && baseTime <= endTime) continue outer;

                }

            }
            final HitObject newObj = new HitObject();

            // 28,192,2804,1,0,0:0:0:0:

            newObj.setObject(HitObjectKeys.X, pos);
            newObj.setObject(HitObjectKeys.Y, 192);
            newObj.setObject(HitObjectKeys.TIME, baseTime);
            newObj.setObject(HitObjectKeys.TYPE, 1);
            newObj.setObject(HitObjectKeys.HIT_SOUND, 0);
            newObj.setObject(HitObjectKeys.OBJECT_PARAMS, "0:0:0:0:");

            chart.getHitObjects().getObjects().add(newObj);
        }

    }

}
