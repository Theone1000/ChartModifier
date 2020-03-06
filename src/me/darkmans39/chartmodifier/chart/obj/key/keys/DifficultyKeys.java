package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class DifficultyKeys {

    private DifficultyKeys() {

    }

    public static NamedKey<Double> HP_DRAIN_RATE = new NamedKey<>(Double.class, "HPDrainRate");
    public static NamedKey<Double> CIRCLE_SIZE = new NamedKey<>(Double.class, "CircleSize");
    public static NamedKey<Double> OVERALL_DIFFICULTY = new NamedKey<>(Double.class, "OverallDifficulty");
    public static NamedKey<Double> APPROACH_RATE = new NamedKey<>(Double.class, "ApproachRate");
    public static NamedKey<Double> SLIDER_MULTIPLIER = new NamedKey<>(Double.class, "SliderMultiplier");
    public static NamedKey<Double> SLIDER_TICKRATE = new NamedKey<>(Double.class, "SliderTickRate");
}
