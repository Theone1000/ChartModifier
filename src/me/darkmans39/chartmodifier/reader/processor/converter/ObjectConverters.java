package me.darkmans39.chartmodifier.reader.processor.converter;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import me.darkmans39.chartmodifier.util.NumberUtil;

public final class ObjectConverters {

    private static final Map<Class<?>, Converter<?>> converters;

    static {
        converters = new HashMap<>();

        add(String.class, (obj) -> obj);
        add(Integer.class, (obj) -> NumberUtil.parseInt(obj, 0));
        add(Float.class, (obj) -> NumberUtil.parseFloat(obj, 0));
        add(Double.class, (obj) -> NumberUtil.parseDouble(obj, 0));

        add(Color.class, (obj) -> {

            final String[] split = obj.split(",", 3);
            System.out.println(split.length);

            return split.length != 3 ? null : new Color(NumberUtil.parseInt(split[0], 0), NumberUtil.parseInt(split[1], 0), NumberUtil.parseInt(split[2], 0));
        });
    }

    public static <T> void add(Class<T> type, Converter<T> converter) {
        converters.put(type, converter);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConverter(Class<T> type) {
        return (T) converters.get(type);
    }

    private ObjectConverters() {

    }

    public static interface Converter<T> {

        T convert(String toConvert);

    }

}
