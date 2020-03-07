package me.darkmans39.chartmodifier.audio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import me.darkmans39.chartmodifier.util.StringUtil;

public final class AudioSpeedup {

    private AudioSpeedup() {

    }

    private static File convertTo(File source, String format, String name) {
        final File target = new File(source.getParentFile(), name + "." + format);
        final AudioAttributes audio = new AudioAttributes();

        audio.setCodec(format.equals("mp3") ? "libmp3lame" : "pcm_s16le");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));

        final EncodingAttributes attrs = new EncodingAttributes();

        attrs.setFormat(format);
        attrs.setAudioAttributes(audio);

        final Encoder encoder = new Encoder();

        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException | EncoderException e) {
            e.printStackTrace();
            return null;
        }

        return target;
    }

    public static File speedUp(File sourceFolder, File source, double rate) {

        final File target = convertTo(source, "wav", "Converted-Wav" + UUID.randomUUID().toString());

        final double tempo = (rate * 100) - 100;

        final File spedUpFile = new File(target.getParentFile(), "SpedUp-" + UUID.randomUUID() + ".wav");
        final File toRun = new File(sourceFolder, "soundstretch.exe");
        final String cmd = String.format("cmd /c start /B %s %s %s -tempo+=%s", toRun.getAbsolutePath(), escape(target.getAbsolutePath()), escape(spedUpFile.getAbsolutePath()), tempo);

        try {
            final Process p = Runtime.getRuntime().exec(cmd);
            final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        File output = convertTo(spedUpFile, "mp3", StringUtil.withoutExtension(source) + " " + rate + "X");

        target.delete();
        spedUpFile.delete();

        return output;
    }

    private static String escape(String i) {
        return "\"" + i + "\"";
    }

}
