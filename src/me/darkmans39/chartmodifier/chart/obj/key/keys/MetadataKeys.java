package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class MetadataKeys {

    private MetadataKeys() {

    }

    public static final NamedKey<String> TITLE = new NamedKey<>(String.class, "Title");
    public static final NamedKey<String> TITLE_UNICODE = new NamedKey<>(String.class, "TitleUnicode");
    public static final NamedKey<String> ARTIST = new NamedKey<>(String.class, "Artist");
    public static final NamedKey<String> ARTIST_UNICODE = new NamedKey<>(String.class, "ArtistUnicode");
    public static final NamedKey<String> CREATOR = new NamedKey<>(String.class, "Creator");
    public static final NamedKey<String> VERSION = new NamedKey<>(String.class, "Version");
    public static final NamedKey<String> SOURCE = new NamedKey<>(String.class, "Source");
    public static final NamedKey<String> TAGS = new NamedKey<>(String.class, "Tags");

    public static final NamedKey<Integer> BEATMAP_ID = new NamedKey<>(Integer.class, "BeatmapID");
    public static final NamedKey<Integer> BEATMAP_SET_ID = new NamedKey<>(Integer.class, "BeatmapSetID");
}
