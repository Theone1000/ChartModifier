package me.darkmans39.chartmodifier.application.chart.mods;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import me.darkmans39.chartmodifier.application.Application;
import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.mods.ModData;
import me.darkmans39.chartmodifier.chart.mods.ModType;
import me.darkmans39.chartmodifier.util.SimpleDocumentListener;

public final class ModFields {

    private final Application application;
    private final Map<String, JTextField> fields;
    private final ModType type;
    private final JRadioButton enableButton;

    public ModFields(Application application, ModType type, JRadioButton enableButton) {
        this.type = type;
        this.application = application;
        this.fields = new HashMap<>();
        this.enableButton = enableButton;
    }

    public ModFields addField(String identifier, JTextField textField) {

        textField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {

            if (application.getTracker().isRemoving()) return;

            if (application.getSelectedChart(application.getUI().getChartTable()) == null) SwingUtilities.invokeLater(() -> textField.setText(""));
        });

        fields.put(identifier, textField);
        return this;
    }

    public void applyFields(Chart chart) {

        final ModData data = chart.getModContainer().getModData(type);

        for (Entry<String, JTextField> entry : fields.entrySet()) {
            data.setObject(entry.getKey(), entry.getValue().getText());
        }

        data.setEnabled(enableButton.isSelected());
    }

    public void updateTextFields(Chart chart) {

        final ModData data = chart.getModContainer().getModData(type);

        for (Entry<String, JTextField> entry : fields.entrySet()) {

            final String newText = data.getObject(entry.getKey());

            entry.getValue().setText(newText == null ? "" : newText);
        }

        enableButton.setSelected(data.isEnabled());

    }

    public void resetModText() {

        for (Entry<String, JTextField> entry : fields.entrySet()) {
            entry.getValue().setText("");
        }

        enableButton.setSelected(false);
    }

    public static final class JTextFieldRadioButtonPair {

        private final JTextField field;
        private final JRadioButton button;

        private JTextFieldRadioButtonPair(JTextField field, JRadioButton button) {
            this.field = field;
            this.button = button;
        }

        public JTextField getField() {
            return field;
        }

        public JRadioButton getButton() {
            return button;
        }

        public static JTextFieldRadioButtonPair of(JTextField field, JRadioButton button) {
            return new JTextFieldRadioButtonPair(field, button);
        }

    }

}
