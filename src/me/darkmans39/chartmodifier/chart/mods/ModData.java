package me.darkmans39.chartmodifier.chart.mods;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class ModData {

    private final Map<String, String> map;
    private boolean isEnabled;

    public ModData() {
        this.map = new HashMap<>();
    }

    public ModData setObject(String key, String value) {
        map.put(key, value);
        return this;
    }

    public String getObject(String key) {
        return map.get(key);
    }

    public String toModString() {
        final StringBuilder builder = new StringBuilder();

        for (Entry<String, String> entry : map.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(" ");
        }

        return builder.length() > 0 ? builder.substring(0, builder.length() - 1) : "";
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
