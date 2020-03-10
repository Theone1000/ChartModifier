package me.darkmans39.chartmodifier.util;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface SimpleDocumentListener extends DocumentListener {

    @Override
    public default void insertUpdate(DocumentEvent e) {
        onUpdate(e);
    }

    @Override
    public default void removeUpdate(DocumentEvent e) {
        onUpdate(e);
    }

    @Override
    public default void changedUpdate(DocumentEvent e) {
        onUpdate(e);

    }

    void onUpdate(DocumentEvent e);

}
