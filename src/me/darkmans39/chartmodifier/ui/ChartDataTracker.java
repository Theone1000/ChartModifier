package me.darkmans39.chartmodifier.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

import me.darkmans39.chartmodifier.chart.Chart;
import me.darkmans39.chartmodifier.chart.obj.key.keys.DifficultyKeys;
import me.darkmans39.chartmodifier.chart.obj.key.keys.MetadataKeys;
import me.darkmans39.chartmodifier.util.NumberUtil;

public final class ChartDataTracker {

    private final Object lock;
    private final List<JTextField> fields;
    private final Application application;

    private boolean isRemoving;

    public ChartDataTracker(Application application) {
        this.fields = new ArrayList<>();
        this.lock = new Object();
        this.application = application;
        register(application.getUI().getChartNameTextField(), "Name");
        register(application.getUI().getChartDifficultyTextField(), "Version");
        register(application.getUI().getRatesTextField(), "Rate");
        register(application.getUI().getHpTextField(), "HP");
        register(application.getUI().getODTextField(), "OD");
    }

    public void resetSelection() {

        final JTable table = application.getUI().getChartTable();
        int max = Math.min(1, table.getRowCount()) - 1;

        if (max > -1) table.getSelectionModel().setSelectionInterval(max, max);

    }

    public void updateText(Chart chart) {

        if (chart == null) return;

        final UI ui = application.getUI();

        ui.getChartNameTextField().setText(chart.getMetadata().getObject(MetadataKeys.TITLE));
        ui.getChartDifficultyTextField().setText(chart.getMetadata().getObject(MetadataKeys.VERSION));
        ui.getRatesTextField().setText(Double.toString(chart.getCachedRate()));
        ui.getHpTextField().setText(chart.getDifficulty().getObject(DifficultyKeys.HP_DRAIN_RATE).toString());
        ui.getODTextField().setText(chart.getDifficulty().getObject(DifficultyKeys.OVERALL_DIFFICULTY).toString());
    }

    public void clearAllText() {
        synchronized (lock) {
            isRemoving = true;
            for (JTextField field : fields) {
                field.setText("");
            }
            isRemoving = false;
        }
    }

    private void register(final JTextField field, String name) {
        fields.add(field);
        field.setName(name);
        field.getDocument().addDocumentListener((SimpleDocumentListener) e -> handle(field, e));
    }

    private void handle(JTextField field, DocumentEvent e) {

        synchronized (lock) {
            if (isRemoving) return;
        }

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
