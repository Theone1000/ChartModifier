package me.darkmans39.chartmodifier.chart.obj.key.keys;

import me.darkmans39.chartmodifier.chart.obj.key.NamedKey;

public final class GeneralKeys {

    private GeneralKeys() {

    }

    public static NamedKey<String> AUDIO_FILE_NAME = new NamedKey<>(String.class, "AudioFilename");
    public static NamedKey<Integer> AUDIO_LEAD_IN = new NamedKey<>(Integer.class, "AudioLeadIn");
    @Deprecated
    public static NamedKey<String> AUDIO_HASH = new NamedKey<>(String.class, "audioHash");
    public static NamedKey<Integer> PREVIEW_TIME = new NamedKey<>(Integer.class, "PreviewTime");
    public static NamedKey<Integer> COUNTDOWN = new NamedKey<>(Integer.class, "Countdown");
    public static NamedKey<String> SAMPLE_SET = new NamedKey<>(String.class, "SampleSet");
    public static NamedKey<Double> STACK_LENIENCY = new NamedKey<>(Double.class, "StackLeniency");
    public static NamedKey<Integer> MODE = new NamedKey<>(Integer.class, "Mode");
    public static NamedKey<Integer> LETTERBOX_IN_BREAKS = new NamedKey<>(Integer.class, "LetterboxInBreaks");
    @Deprecated
    public static NamedKey<Integer> STORY_FIRE_IN_FRONT = new NamedKey<>(Integer.class, "StoryFireInFront");
    public static NamedKey<Integer> USE_SKIN_SPRITES = new NamedKey<>(Integer.class, "UseSkinSprites");
    @Deprecated
    public static NamedKey<Integer> ALWAYS_SHOW_PLAYFIELD = new NamedKey<>(Integer.class, "AlwaysShowPlayField");
    public static NamedKey<String> OVERLAY_POSITION = new NamedKey<>(String.class, "OverlayPosition");
    public static NamedKey<String> SKIN_PREFERENCE = new NamedKey<>(String.class, "SkinPreference");
    public static NamedKey<Integer> EPILEPSY_WARNING = new NamedKey<>(Integer.class, "EpilepsyWarning");
    public static NamedKey<Integer> COUNTDOWN_OFFSET = new NamedKey<>(Integer.class, "CountdownOffset");
    public static NamedKey<Integer> SPECIAL_STYLE = new NamedKey<>(Integer.class, "SpecialStyle");
    public static NamedKey<Integer> WIDESCREEN_STORYBOARD = new NamedKey<>(Integer.class, "WidescreenStoryboard");
    public static NamedKey<Integer> SAMPLES_MATCH_PLAYBACK_RATE = new NamedKey<>(Integer.class, "SamplesMatchPlaybackRate");
    
    
}
