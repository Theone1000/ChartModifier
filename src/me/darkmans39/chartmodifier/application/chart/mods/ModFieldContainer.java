package me.darkmans39.chartmodifier.application.chart.mods;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import me.darkmans39.chartmodifier.application.Application;
import me.darkmans39.chartmodifier.application.UI;
import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.mods.ModType;

public final class ModFieldContainer {

    private final Application application;
    private final List<ModFields> fields;

    public ModFieldContainer(Application application) {
        this.fields = new ArrayList<>();
        this.application = application;

        UI ui = application.getUI();

        final ModFields quads = newModField(ModType.QUADS, ui.getModQuadEnabledRadioButton());

        quads.addField("density", ui.getModQuadDensityTextField());
    }

    public ModFields newModField(ModType type, JRadioButton enableButton) {

        enableButton.addItemListener((e) -> {
            if (application.getSelectedChart(application.getUI().getChartTable()) == null) enableButton.setSelected(false);
        });

        final ModFields field = new ModFields(application, type, enableButton);

        fields.add(field);

        return field;
    }

    public void registerField(ModFields field) {
        fields.add(field);
    }

    public void applyAllFields(Chart chart) {
        System.out.println("Applying fields: " + chart.toString());

        for (ModFields field : fields) {
            field.applyFields(chart);
        }

    }

    public void updateAllTextFields(Chart chart) {
        System.out.println("Updating fields");

        for (ModFields field : fields) {
            field.updateTextFields(chart);
        }
    }

    public void clearAllText() {

        for (ModFields field : fields) {
            field.resetModText();
        }

    }

}
