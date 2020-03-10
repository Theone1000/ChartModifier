package me.darkmans39.chartmodifier.chart.mods.registry;

import java.util.HashMap;
import java.util.Map;

import me.darkmans39.chartmodifier.chart.mods.Mod;
import me.darkmans39.chartmodifier.chart.mods.ModType;

public final class ModRegistry {

    private static final Map<ModType, Mod> mods;

    static {
        mods = new HashMap<>();

        registerMod(ModType.QUADS, new QuadMod());
    }

    private ModRegistry() {

    }

    public static final Mod getMod(ModType type) {
        return mods.get(type);
    }

    public static final void registerMod(ModType type, Mod mod) {
        mods.put(type, mod);
    }

}
