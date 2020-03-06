package me.darkmans39.chartmodifier.util;

import java.util.StringJoiner;

public final class AppendUtil {

    private AppendUtil() {

    }

    public static void appendNonNull(StringJoiner joiner, Object obj) {
        if (obj != null) joiner.add(obj.toString());
    }

}
