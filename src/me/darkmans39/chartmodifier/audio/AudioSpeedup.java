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

    public static File speedUp(File source, double rate) {

        final File target = convertTo(source, "wav", "Converted-" + UUID.randomUUID().toString());

        double tempo = (rate * 100) - 100;

        final File path = target.getParentFile();
        final File spedUpFile = new File(path, "SpedUp-" + UUID.randomUUID() + ".wav");
        final String cmd = String.format("cmd /c start /B C:\\Users\\Kaleb\\Desktop\\cyber\\soundstretch.exe %s %s -tempo+=%s", target.getAbsolutePath(), spedUpFile.getAbsolutePath(), tempo);

        try {
            System.out.println(cmd);
            final Process p = Runtime.getRuntime().exec(cmd);
            final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        convertTo(spedUpFile, "mp3", "Converted MP3");

        target.delete();
        spedUpFile.delete();

        return null;
    }

}
