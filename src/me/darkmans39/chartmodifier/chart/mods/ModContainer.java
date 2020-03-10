package me.darkmans39.chartmodifier.chart.mods;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class ModContainer {

    private final Map<ModType, ModData> map;

    public ModContainer() {
        this.map = new TreeMap<>();

        for (ModType type : ModType.values()) {
            map.put(type, new ModData());
        }
    }

    public ModData getModData(ModType type) {
        return map.get(type);
    }

    public Set<Entry<ModType, ModData>> getModData() {
        return map.entrySet();
    }

}
