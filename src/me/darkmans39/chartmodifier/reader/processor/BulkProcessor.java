package me.darkmans39.chartmodifier.reader.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.container.Container;
import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;
import me.darkmans39.chartmodifier.reader.processor.converter.ObjectConverters;
import me.darkmans39.chartmodifier.reader.processor.converter.ObjectConverters.Converter;

public abstract class BulkProcessor implements Processor {

    private static final Map<String, Field> cachedFields = new HashMap<>();

    /*
     * 
     */

    private Map<String, Processor> processors;

    public BulkProcessor() {
        this.processors = new HashMap<>();
    }

    public void addProcessor(String identifier, Processor processor) {
        processors.put(identifier, processor);
    }

    @SuppressWarnings("unchecked")
    public <T> void addGenericProcessorsOfType(Class<T> type, Class<?> keyClass, String containerFieldName) {

        Field containerField = cachedFields.get(containerFieldName);

        if (containerField == null) {

            try {
                Field toCache = Chart.class.getDeclaredField(containerFieldName);
                toCache.setAccessible(true);

                cachedFields.put(containerFieldName, containerField = toCache);
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
                return;
            }

        }

        final Field finalContainerField = containerField;

        for (Field field : keyClass.getFields()) {

            if (!Modifier.isStatic(field.getModifiers()) || !field.getType().isAssignableFrom(NamedKey.class)) continue;

            final NamedKey<T> key;

            try {
                key = (NamedKey<T>) field.get(null);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }

            if (key.getType() != type) continue;

            final Converter<T> converter = (Converter<T>) ObjectConverters.getConverter(type);

            if (converter == null) continue;

            addProcessor(key.getName(), (line, appendTo) -> {

                Container<?> container;

                try {
                    container = (Container<?>) finalContainerField.get(appendTo.getChart());
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }

                container.setObject(key, converter.convert(line));
            });

        }

    }

    public Processor getProcessor(String identifier) {
        return processors.get(identifier);
    }

    public abstract boolean isSemicolonSplitData();

    public void onStart() {

    }

}
