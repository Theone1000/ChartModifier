package me.darkmans39.chartmodifier.chart.obj.container;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public abstract class Container<S> implements OsuString {

    private final String identifier;
    private final Map<NamedKey<?>, Object> data;
    private final boolean space;

    public Container(String identifier, boolean space) {
        this.space = space;
        this.identifier = identifier;
        this.data = new LinkedHashMap<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(NamedKey<T> key) {
        return (T) data.get(key);
    }

    public <T> S setObject(NamedKey<T> key, T value) {
        data.put(key, value);
        return getThis();
    }

    public abstract S getThis();

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();

        builder.append("Class=" + getClass().getCanonicalName()).append("\n");

        for (Entry<NamedKey<?>, Object> obj : data.entrySet()) {
            builder.append("---> ").append(obj.getKey().getName()).append("=").append(obj.getValue()).append("\n");
        }

        return builder.toString();
    }

    @Override
    public String toOsuString() {
        if (identifier == null) return "";

        final StringBuilder builder = new StringBuilder();

        builder.append("[" + identifier + "]").append("\n");

        for (Entry<NamedKey<?>, Object> obj : data.entrySet()) {
            builder.append(obj.getKey().getName()).append(":");
            if (space) builder.append(" ");

            Object value = obj.getValue();

            if (value instanceof Double || value instanceof Float) {

                final double d = (double) value;

                if (Math.floor(d) == d) value = (int) d;
            }

            builder.append(value).append("\n");
        }

        return builder.toString();
    }

}
