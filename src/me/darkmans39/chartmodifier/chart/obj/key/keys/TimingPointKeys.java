package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class TimingPointKeys {

    private TimingPointKeys() {

    }

    public static NamedKey<Integer> TIME = new NamedKey<>(Integer.class, "Time");
    public static NamedKey<Double> BEAT_LENGTH = new NamedKey<>(Double.class, "BeatLength");
    public static NamedKey<Integer> METER = new NamedKey<>(Integer.class, "Meter");
    public static NamedKey<Integer> SAMPLE_SET = new NamedKey<>(Integer.class, "SampleSet");
    public static NamedKey<Integer> SAMPLE_INDEX = new NamedKey<>(Integer.class, "SampleIndex");
    public static NamedKey<Integer> VOLUME = new NamedKey<>(Integer.class, "Volume");
    public static NamedKey<Integer> UNINHERITED = new NamedKey<>(Integer.class, "Uninherited");
    public static NamedKey<Integer> EFFECTS = new NamedKey<>(Integer.class, "Effects");
}
