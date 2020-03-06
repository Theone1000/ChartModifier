package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class HitObjectKeys {

    private HitObjectKeys() {

    }

    public static NamedKey<Integer> X = new NamedKey<>(Integer.class, "X");
    public static NamedKey<Integer> Y = new NamedKey<>(Integer.class, "Y");
    public static NamedKey<Integer> TIME = new NamedKey<>(Integer.class, "Time");
    public static NamedKey<Integer> TYPE = new NamedKey<>(Integer.class, "Type");
    public static NamedKey<String> OBJECT_PARAMS = new NamedKey<>(String.class, "ObjectParams");
    public static NamedKey<Integer> HIT_SOUND = new NamedKey<>(Integer.class, "HitSound");
    public static NamedKey<Integer> HIT_SAMPLE = new NamedKey<>(Integer.class, "HitSample");
}
