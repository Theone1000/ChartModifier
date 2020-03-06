package me.darkmans39.chartmodifier.chart.obj.list;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import me.darkmans39.chartmodifier.chart.obj.container.OsuString;

public abstract class ListContainer<T extends OsuString> implements OsuString {

    private final List<T> objects;
    private final String identifier;
    private final boolean includeDelimiters;

    public ListContainer(String identifier, boolean includeDelimiters) {
        this.identifier = identifier;
        this.includeDelimiters = includeDelimiters;
        this.objects = new ArrayList<>();
    }

    public List<T> getObjects() {
        return objects;
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
