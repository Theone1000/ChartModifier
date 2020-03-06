package me.darkmans39.chartmodifier.util;

public final class NumberUtil {

    private NumberUtil() {

    }

    public static int parseInt(String input, int def) {

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
            return def;
        }

    }

    public static float parseFloat(String input, float def) {

        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException ignored) {
            return def;
        }

    }

    public static double parseDouble(String input, double def) {

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
            return def;
        }

    }

}
