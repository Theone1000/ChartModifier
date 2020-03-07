package me.darkmans39.chartmodifier.util;

import java.io.File;
import java.util.StringJoiner;

public final class StringUtil {

    private StringUtil() {

    }

    public static void appendNonNull(StringJoiner joiner, Object obj) {
        if (obj != null) joiner.add(obj.toString());
    }

    public static String withoutExtension(File file) {

        int lastIndex = file.getName().lastIndexOf('.');

        return lastIndex == -1 ? file.getName() : file.getName().substring(0, lastIndex);
    }

}
