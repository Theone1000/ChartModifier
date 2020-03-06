package me.darkmans39.chartmodifier.ui;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class ChartDataTracker {

    private final Application application;

    public ChartDataTracker(Application application) {
        this.application = application;
        register(application.getUI().getChartNameTextField(), "Name");
        register(application.getUI().getChartDifficultyTextField(), "Version");
        register(application.getUI().getRatesTextField(), "Rate");
        register(application.getUI().getHpTextField(), "HP");
        register(application.getUI().getODTextField(), "OD");
    }

    private void register(final JTextField field, String name) {

        field.setName(name);

        field.getDocument().addDocumentListener((SimpleDocumentListener) e -> handle(field, e));
    }

    private void handle(JTextField field, DocumentEvent e) {

        final Chart chart = application.getSelectedChart();

        if (chart == null) {
            SwingUtilities.invokeLater(() -> field.setText(""));
            return;
        }

        switch (field.getName()) {
        case "Name":
            chart.getMetadata().setObject(MetadataKeys.TITLE, field.getText());
            break;
        case "Version":
            chart.getMetadata().setObject(MetadataKeys.VERSION, field.getText());
            break;
        case "Rate":
            chart.setCachedRate(NumberUtil.parseDouble(field.getText(), 1));
            break;
        case "HP":
            chart.getDifficulty().setObject(DifficultyKeys.HP_DRAIN_RATE, NumberUtil.parseDouble(field.getText(), 8));
            break;
        case "OD":
            chart.getDifficulty().setObject(DifficultyKeys.OVERALL_DIFFICULTY, NumberUtil.parseDouble(field.getText(), 8));
            break;
        }

        SwingUtilities.invokeLater(() -> {

            DefaultTableModel model = (DefaultTableModel) application.getUI().getChartTable().getModel();

            model.fireTableRowsUpdated(application.getUI().getChartTable().getSelectedRow(), application.getUI().getChartTable().getSelectedRow());
        });

    }

}
