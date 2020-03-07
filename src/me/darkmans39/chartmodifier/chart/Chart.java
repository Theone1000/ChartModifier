package me.darkmans39.chartmodifier.chart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import me.darkmans39.chartmodifier.chart.obj.container.containers.Difficulty;
import me.darkmans39.chartmodifier.chart.obj.container.containers.Editor;
import me.darkmans39.chartmodifier.chart.obj.container.containers.General;
import me.darkmans39.chartmodifier.chart.obj.container.containers.HitObject;
import me.darkmans39.chartmodifier.chart.obj.container.containers.Metadata;
import me.darkmans39.chartmodifier.chart.obj.container.containers.TimingPoint;
import me.darkmans39.chartmodifier.chart.obj.container.containers.events.Break;
import me.darkmans39.chartmodifier.chart.obj.container.containers.events.Events;
import me.darkmans39.chartmodifier.chart.obj.key.keys.GeneralKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.HitObjectKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.TimingPointKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.BreakKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.EventKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.events.GenericEventKeys;
import me.darkmans39.chartmodifier.chart.obj.list.lists.HitObjects;
import me.darkmans39.chartmodifier.chart.obj.list.lists.TimingPoints;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class Chart {

    public static Chart ofNone() {
        return new Chart(null);
    }

    public static Chart of(File file) {

        final Chart chart = new Chart(file);

        ChartReader.readTo(file, chart);

        return chart;
    }

    /*
     * 
     */

    private File file;
    private double cachedRate;
    private final General general;
    private final Metadata metadata;
    private final Editor editor;
    private final Difficulty difficulty;
    private final HitObjects hitObjects;
    private final TimingPoints timingPoints;
    private final Events events;

    private Chart(File file) {
        this.file = file;
        this.cachedRate = 1;
        this.general = new General();
        this.metadata = new Metadata();
        this.editor = new Editor();
        this.difficulty = new Difficulty();
        this.hitObjects = new HitObjects();
        this.timingPoints = new TimingPoints();
        this.events = new Events();
    }

    public File getFile() {
        return file;
    }

    public void setCachedRate(double cachedRate) {
        this.cachedRate = cachedRate;
    }

    public double getCachedRate() {
        return cachedRate;
    }

    public TimingPoints getTimingPoints() {
        return timingPoints;
    }

    public Events getEvents() {
        return events;
    }

    public HitObjects getHitObjects() {
        return hitObjects;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public General getGeneral() {
        return general;
    }

    public Editor getEditor() {
        return editor;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Chart uprate(double rate) {

        for (HitObject obj : hitObjects.getObjects()) {

            obj.setObject(HitObjectKeys.TIME, increaseSpeedRound(rate, obj.getObject(HitObjectKeys.TIME)));

            final String params = obj.getObject(HitObjectKeys.OBJECT_PARAMS);

            if (params != null) {
                final String[] split = params.split(":");

                if (!split[0].equals("0") && !params.contains("|")) {

                    final int index = params.indexOf(':');
                    final int spedUp = increaseSpeedRound(rate, NumberUtil.parseInt(params.substring(0, index), 0));

                    obj.setObject(HitObjectKeys.OBJECT_PARAMS, spedUp + params.substring(index));

                }
            }
        }

        for (TimingPoint point : timingPoints.getObjects()) {

            point.setObject(TimingPointKeys.TIME, increaseSpeedRound(rate, point.getObject(TimingPointKeys.TIME)));

            int uninherited = point.getObject(TimingPointKeys.UNINHERITED);

            if (uninherited == 1) point.setObject(TimingPointKeys.BEAT_LENGTH, increaseSpeed(rate, point.getObject(TimingPointKeys.BEAT_LENGTH)));

        }

        for (Break obj : events.getObject(EventKeys.BREAKS).getObjects()) {

            obj.setObject(GenericEventKeys.START_TIME, increaseSpeedRound(rate, obj.getObject(GenericEventKeys.START_TIME)));
            obj.setObject(BreakKeys.END_TIME, increaseSpeedRound(rate, obj.getObject(BreakKeys.END_TIME)));
        }

        metadata.setObject(MetadataKeys.BEATMAP_SET_ID, -1);

        final int value = general.getObject(GeneralKeys.PREVIEW_TIME);

        if (value > 0) general.setObject(GeneralKeys.PREVIEW_TIME, increaseSpeedRound(rate, value - 40));

        return this;
    }

    public Chart write(File output) {

        try {
            output.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }

        try (FileWriter writer = new FileWriter(output)) {

            writer.append("osu file format v14").append("\n\n");
            writer.append(general.toOsuString()).append("\n");
            writer.append(editor.toOsuString()).append("\n");
            writer.append(metadata.toOsuString()).append("\n");
            writer.append(difficulty.toOsuString()).append("\n");
            writer.append(events.toOsuString()).append("\n");
            writer.append(timingPoints.toOsuString()).append("\n\n");
            writer.append(hitObjects.toOsuString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Wrote file: " + output);

        return this;
    }

    public static int increaseSpeedRound(double rate, int input) {
        return (int) (Math.round((double) input / rate)) + 40;
    }

    public static double increaseSpeed(double rate, double input) {
        return (input / rate);
    }

    @Override
    public String toString() {
        return metadata.getObject(MetadataKeys.TITLE) + " | " + metadata.getObject(MetadataKeys.VERSION);
    }

}
