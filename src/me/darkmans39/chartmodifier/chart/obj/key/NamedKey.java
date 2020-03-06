package me.darkmans39.chartmodifier.chart.obj.key;

import java.util.Objects;

public final class NamedKey<T> implements Key<T> {

    private final Class<T> type;
    private final String name;

    public NamedKey(Class<T> type, String name) {
        this.name = name;
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NamedKey)) return false;

        final NamedKey<?> other = (NamedKey<?>) obj;

        return Objects.equals(name, other.name);
    }

}
