package me.darkmans39.chartmodifier.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ConsoleWriter {

    private final Object lock;
    private final DateTimeFormatter formatter;
    private final Application application;

    public ConsoleWriter(Application application) {
        this.lock = new Object();
        this.formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        this.application = application;
    }

    public void clear() {

        synchronized (lock) {
            application.getUI().getConsoleLogTextArea().setText("");
        }

    }

    public void println(String text, ConsoleWriteType type) {

        final String date = formatter.format(LocalDateTime.now());

        synchronized (lock) {
            application.getUI().getConsoleLogTextArea().append("[" + date + " " + type + "] " + text + "\n");
        }

    }

    public enum ConsoleWriteType {
        ERROR,
        SUCCESS;
    }

}
