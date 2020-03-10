package me.darkmans39.chartmodifier.chart.obj.list;

import java.util.Collection;
import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.OsuString;

public abstract class ListContainer<T extends OsuString, C extends Collection<T>> implements OsuString {

    private final C objects;
    private final String identifier;
    private final boolean includeDelimiters;

    public ListContainer(String identifier, boolean includeDelimiters, C impl) {
        this.identifier = identifier;
        this.includeDelimiters = includeDelimiters;
        this.objects = impl;
    }

    public C getObjects() {
        return objects;
    }

    public boolean isIncludeDelimiters() {
        return includeDelimiters;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();

        builder.append("class=" + getClass().getCanonicalName()).append("\n");
        builder.append("---> ").append(identifier).append(":").append(objects.size());

        return builder.toString();
    }

    @Override
    public String toOsuString() {

        final StringJoiner joiner = new StringJoiner("\n");

        joiner.add(includeDelimiters ? "[" + identifier + "]" : identifier);

        for (T obj : getObjects()) {
            joiner.add(obj.toOsuString());
        }

        return joiner.toString() + "\n";
    }
}
