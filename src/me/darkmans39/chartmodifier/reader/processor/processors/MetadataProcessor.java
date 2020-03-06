package me.darkmans39.chartmodifier.reader.processor.processors;

import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.reader.ChartReader;
import me.darkmans39.chartmodifier.reader.processor.BulkProcessor;

public final class MetadataProcessor extends BulkProcessor {

    public MetadataProcessor() {
        addGenericProcessorsOfType(String.class, MetadataKeys.class, "metadata");
        addGenericProcessorsOfType(Integer.class, MetadataKeys.class, "metadata");

//        addProcessor(MetadataKeys.TITLE.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.TITLE, line));
//        addProcessor(MetadataKeys.TITLE_UNICODE.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.TITLE_UNICODE, line));
//
//        addProcessor(MetadataKeys.ARTIST.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.ARTIST, line));
//        addProcessor(MetadataKeys.ARTIST_UNICODE.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.ARTIST_UNICODE, line));
//
//        addProcessor(MetadataKeys.CREATOR.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.CREATOR, line));
//
//        addProcessor(MetadataKeys.VERSION.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.VERSION, line));
//
//        addProcessor(MetadataKeys.SOURCE.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.SOURCE, line));
//
//        addProcessor(MetadataKeys.TAGS.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.TAGS, line));
//
//        addProcessor(MetadataKeys.BEATMAP_ID.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.BEATMAP_ID, NumberUtil.parseInt(line, -1)));
//        addProcessor(MetadataKeys.BEATMAP_SET_ID.getName(), (line, appendTo) -> appendTo.getChart().getMetadata().setObject(MetadataKeys.BEATMAP_SET_ID, NumberUtil.parseInt(line, -1)));
    }

    @Override
    public void process(String line, ChartReader appendTo) {

    }

    @Override
    public boolean isSemicolonSplitData() {
        return true;
    }

}
