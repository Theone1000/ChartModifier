package me.darkmans39.chartmodifier.application.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.darkmans39.chartmodifier.application.Application;

public final class StorageContainer {

    private final Gson gson;
    private final Application application;
    private Storage storage;

    public StorageContainer(final Application application) {
        this.gson = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create();
        this.application = application;
        load();

        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> save());
            }

        }, 1000l, 1000l);

    }

    public void updateOsuDirectoryText() {

        final File file = storage.osuDirectory;
        final JTextField field = application.getUI().getCurrentOsuDirectoryTextField();

        if (file == null) {
            field.setText("");
            return;
        }

        field.setText(file.toString());
    }

    public void load() {

        final File file = new File(application.getDirectory(), "config.json");

        Storage storage = null;

        if (!file.exists()) {
            storage = new Storage();
        } else {
            try (FileReader reader = new FileReader(file)) {
                storage = gson.fromJson(reader, Storage.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.storage = storage == null ? new Storage() : storage;

    }

    public void save() {

        final File file = new File(application.getDirectory(), "config.json");

        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(storage, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Storage getStorage() {
        return storage;
    }

    public static class Storage {
        private File osuDirectory;

        public File getOsuDirectory() {
            return osuDirectory;
        }

        public void setOsuDirectory(File osuDirectory) {
            this.osuDirectory = osuDirectory;
        }
    }

}
