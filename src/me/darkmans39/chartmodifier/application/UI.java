package me.darkmans39.chartmodifier.application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class UI {

    private JFrame frame;
    private JTable chartTable;
    private JTextField chartNameTextField;
    private JTextField RatesTextField;
    private JTextField hpTextField;
    private JTextField ODTextField;
    private JTextField chartDifficultyTextField;
    private JButton loadFromFileButton;
    private JButton removeDifficulty;
    private JButton duplicateButton;
    private JButton writeAllDifficultiesButton;
    private JTable writingDifficultiesTable;
    private JScrollPane scrollPane_2;
    private JTextArea consoleLogTextArea;
    private JLabel lblNewLabel;
    private JTextField massApplyRatesTextField;
    private JLabel lblShiftOrCtrl;
    private JButton massApplyRatesButton;
    private JButton removeAllSelectedChartsButton;
    private JButton consoleClearButton;
    private JRadioButton autoScanChartsRadioButton;
    private JTextField currentOsuDirectoryTextField;
    private JButton updateOsuDirectoryButton;
    private JRadioButton keepPitchRadioButton;
    private JPanel panel_4;
    private JTextField modQuadDensityTextField;
    private JRadioButton modQuadEnabledRadioButton;
    private JButton openChartDirectoryButton;
    private JButton clearLoadCacheButton;
    private JRadioButton UnloadAllChartsAfterWriteRadioButton;
    private JPanel panel_5;
    private JScrollPane scrollPane_3;
    private JTable chartHistoryTable;
    private JButton reloadChartButton;
    private JButton openChartHistoryDirectoryButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    UI window = new UI();
                    window.frame.setVisible(true);
                    window.frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UI() {
        initialize();
    }

    public JTextField getChartDifficultyTextField() {
        return chartDifficultyTextField;
    }

    public JTextField getChartNameTextField() {
        return chartNameTextField;
    }

    public JTextField getHpTextField() {
        return hpTextField;
    }

    public JTextField getODTextField() {
        return ODTextField;
    }

    public JTextField getRatesTextField() {
        return RatesTextField;
    }

    public JButton getLoadFromFileButton() {
        return loadFromFileButton;
    }

    public JButton getDuplicateButton() {
        return duplicateButton;
    }

    public JButton getRemoveDifficulty() {
        return removeDifficulty;
    }

    public JButton getWriteAllDifficultiesButton() {
        return writeAllDifficultiesButton;
    }

    public JTable getChartTable() {
        return chartTable;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getWritingDifficultiesTable() {
        return writingDifficultiesTable;
    }

    public JTextArea getConsoleLogTextArea() {
        return consoleLogTextArea;
    }

    public JButton getMassApplyRatesButton() {
        return massApplyRatesButton;
    }

    public JTextField getMassApplyRatesTextField() {
        return massApplyRatesTextField;
    }

    public JButton getRemoveAllSelectedChartsButton() {
        return removeAllSelectedChartsButton;
    }

    public JButton getConsoleClearButton() {
        return consoleClearButton;
    }

    public JRadioButton getAutoScanChartsRadioButton() {
        return autoScanChartsRadioButton;
    }

    public JTextField getCurrentOsuDirectoryTextField() {
        return currentOsuDirectoryTextField;
    }

    public JButton getUpdateOsuDirectoryButton() {
        return updateOsuDirectoryButton;
    }

    public JRadioButton getKeepPitchRadioButton() {
        return keepPitchRadioButton;
    }

    public JTextField getModQuadDensityTextField() {
        return modQuadDensityTextField;
    }

    public JRadioButton getModQuadEnabledRadioButton() {
        return modQuadEnabledRadioButton;
    }

    public JButton getOpenChartDirectoryButton() {
        return openChartDirectoryButton;
    }

    public JButton getClearLoadCacheButton() {
        return clearLoadCacheButton;
    }

    public JRadioButton getUnloadAllChartsAfterWriteRadioButton() {
        return UnloadAllChartsAfterWriteRadioButton;
    }

    public JTable getChartHistoryTable() {
        return chartHistoryTable;
    }

    public JButton getReloadChartFromHistoryButton() {
        return reloadChartButton;
    }

    public JButton getOpenChartHistoryDirectoryButton() {
        return openChartHistoryDirectoryButton;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        ToolTipManager.sharedInstance().setInitialDelay(500);
        ToolTipManager.sharedInstance().setReshowDelay(500);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

        UIManager.put("ToolTip.background", new ColorUIResource(255, 255, 255));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(new Color(76, 79, 83)));

        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("unknown.png")));
        frame.setTitle("Oniichan42's Rate Converter");
        frame.setResizable(false);
        frame.setBounds(100, 100, 1318, 634);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 395, 409, 187);
        frame.getContentPane().add(scrollPane);

        DefaultTableModel beingWrittenTable = new DefaultTableModel();

        beingWrittenTable.addColumn("Charts Being Written");

        DefaultTableModel chartsModel = new DefaultTableModel();

        chartsModel.addColumn("Charts");

        chartTable = new JTable();
        chartTable.setDefaultEditor(Object.class, null);
        chartTable.setModel(chartsModel);
        // table.getColumn("Charts").setPreferredWidth(160);
        chartTable.setFont(new Font("Segoe UI", Font.BOLD, 10));
        scrollPane.setViewportView(chartTable);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        panel.setBounds(10, 24, 409, 331);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblChartName = new JLabel("Chart Name");
        lblChartName.setBounds(10, 0, 374, 39);
        panel.add(lblChartName);

        chartNameTextField = new JTextField();
        chartNameTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        chartNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
        chartNameTextField.setBounds(10, 30, 374, 25);
        panel.add(chartNameTextField);
        chartNameTextField.setColumns(10);

        JLabel lblRatescommaSeparated = new JLabel("Rate");
        lblRatescommaSeparated.setBounds(10, 103, 39, 39);
        panel.add(lblRatescommaSeparated);

        RatesTextField = new JTextField();
        RatesTextField.setHorizontalAlignment(SwingConstants.LEFT);
        RatesTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        RatesTextField.setColumns(10);
        RatesTextField.setBounds(10, 133, 131, 25);
        panel.add(RatesTextField);

        JLabel lblHp = new JLabel("HP");
        lblHp.setBounds(10, 158, 29, 39);
        panel.add(lblHp);

        hpTextField = new JTextField();
        hpTextField.setHorizontalAlignment(SwingConstants.LEFT);
        hpTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        hpTextField.setColumns(10);
        hpTextField.setBounds(10, 188, 62, 25);
        panel.add(hpTextField);

        ODTextField = new JTextField();
        ODTextField.setHorizontalAlignment(SwingConstants.LEFT);
        ODTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        ODTextField.setColumns(10);
        ODTextField.setBounds(79, 188, 62, 25);
        panel.add(ODTextField);

        JLabel lblOd = new JLabel("OD");
        lblOd.setBounds(79, 158, 29, 39);
        panel.add(lblOd);

        chartDifficultyTextField = new JTextField();
        chartDifficultyTextField.setHorizontalAlignment(SwingConstants.LEFT);
        chartDifficultyTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        chartDifficultyTextField.setColumns(10);
        chartDifficultyTextField.setBounds(10, 87, 374, 25);
        panel.add(chartDifficultyTextField);

        JLabel lblChartDifficulty = new JLabel("Chart Difficulty");
        lblChartDifficulty.setBounds(10, 50, 374, 39);
        panel.add(lblChartDifficulty);

        duplicateButton = new JButton("Duplicate Chart");
        duplicateButton.setToolTipText("Duplicates the highlighted chart and adds it to the Chart view.");
        duplicateButton.setBounds(10, 297, 107, 23);
        panel.add(duplicateButton);

        removeDifficulty = new JButton("Unload Chart");
        removeDifficulty.setToolTipText("Unloads the highlighted chart and removes it from the Chart view.");
        removeDifficulty.setBounds(127, 297, 107, 23);
        panel.add(removeDifficulty);

        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        tabbedPane_1.setBounds(151, 132, 233, 158);
        panel.add(tabbedPane_1);

        panel_4 = new JPanel();
        tabbedPane_1.addTab("Quads", null, panel_4, null);
        panel_4.setLayout(null);

        modQuadDensityTextField = new JTextField();
        modQuadDensityTextField.setBounds(10, 95, 86, 20);
        panel_4.add(modQuadDensityTextField);
        modQuadDensityTextField.setColumns(10);

        JLabel lblEveryXNote = new JLabel("Every X note");
        lblEveryXNote.setBounds(10, 59, 86, 39);
        panel_4.add(lblEveryXNote);

        modQuadEnabledRadioButton = new JRadioButton("Enable mod");
        modQuadEnabledRadioButton.setBounds(109, 94, 109, 23);
        panel_4.add(modQuadEnabledRadioButton);

        JLabel lblMods = new JLabel("Mods");
        lblMods.setBounds(151, 103, 39, 39);
        panel.add(lblMods);

        openChartDirectoryButton = new JButton("Open Chart Directory");
        openChartDirectoryButton.setToolTipText("Opens the higlighted chart's directory.");
        openChartDirectoryButton.setBounds(244, 297, 140, 23);
        panel.add(openChartDirectoryButton);

        JLabel lblCurrentDifficulty = new JLabel("Selected Chart");
        lblCurrentDifficulty.setBounds(10, 0, 119, 32);
        frame.getContentPane().add(lblCurrentDifficulty);

        loadFromFileButton = new JButton("Load From File");
        loadFromFileButton.setToolTipText("Tip: You can highlight multiple .osu files to add more than one chart at a time.");
        loadFromFileButton.setBounds(10, 366, 161, 23);
        frame.getContentPane().add(loadFromFileButton);

        writeAllDifficultiesButton = new JButton("Write All Charts");
        writeAllDifficultiesButton.setToolTipText(
                "<html>\r\nWrites all of the loaded charts as .osu files in their respective directories to be playable in osu!, then optionally (if \"Unload All Charts After Write is enabled\") unloads all of the loaded charts.\r\n</html>");
        writeAllDifficultiesButton.setBounds(799, 366, 142, 23);
        frame.getContentPane().add(writeAllDifficultiesButton);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(425, 395, 516, 187);
        frame.getContentPane().add(scrollPane_1);
        writingDifficultiesTable = new JTable();
        writingDifficultiesTable.setDefaultEditor(Object.class, null);
        writingDifficultiesTable.setModel(beingWrittenTable);
        writingDifficultiesTable.setFont(new Font("Segoe UI", Font.BOLD, 10));
        scrollPane_1.setViewportView(writingDifficultiesTable);

        JLabel lblLog = new JLabel("Log");
        lblLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblLog.setBounds(429, 0, 34, 32);
        frame.getContentPane().add(lblLog);

        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        scrollPane_2.setBounds(425, 24, 516, 331);
        frame.getContentPane().add(scrollPane_2);

        consoleLogTextArea = new JTextArea();
        consoleLogTextArea.setToolTipText("");
        consoleLogTextArea.setLineWrap(true);
        consoleLogTextArea.setFont(new Font("Segoe UI", Font.BOLD, 10));

        consoleLogTextArea.setEditable(false);
        scrollPane_2.setViewportView(consoleLogTextArea);
        consoleLogTextArea.setColumns(10);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        tabbedPane.setBounds(951, 24, 351, 227);
        frame.getContentPane().add(tabbedPane);

        JPanel panel_1 = new JPanel();
        panel_1.setToolTipText("");
        tabbedPane.addTab("Mass Rate Applier", panel_1);
        panel_1.setLayout(null);

        lblNewLabel = new JLabel("Rates (separate each rate by a space)");
        lblNewLabel.setBounds(10, 0, 195, 23);
        panel_1.add(lblNewLabel);

        massApplyRatesButton = new JButton("Duplicate highlighted charts, then apply rates.");
        massApplyRatesButton.setBounds(10, 161, 326, 23);
        panel_1.add(massApplyRatesButton);

        massApplyRatesTextField = new JTextField();
        massApplyRatesTextField.setHorizontalAlignment(SwingConstants.LEFT);
        massApplyRatesTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        massApplyRatesTextField.setColumns(10);
        massApplyRatesTextField.setBounds(10, 24, 195, 25);
        panel_1.add(massApplyRatesTextField);
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Auto Chart Loader", panel_2);
        panel_2.setLayout(null);

        autoScanChartsRadioButton = new JRadioButton("Enabled");
        autoScanChartsRadioButton.setBounds(263, 165, 69, 23);
        panel_2.add(autoScanChartsRadioButton);

        lblShiftOrCtrl = new JLabel("Highlight multiple entries by using shift or control then clicking an entry (Select an entry then press `CTRL + A` to highlight all entires in that table)");
        lblShiftOrCtrl.setBounds(10, 582, 747, 23);
        frame.getContentPane().add(lblShiftOrCtrl);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Config", panel_3);
        panel_3.setLayout(null);

        updateOsuDirectoryButton = new JButton("Update osu! directory");
        updateOsuDirectoryButton.setBounds(10, 161, 161, 23);
        panel_3.add(updateOsuDirectoryButton);

        JLabel lblCurrentOsuDirectory = new JLabel("Current osu! directory");
        lblCurrentOsuDirectory.setBounds(10, 11, 195, 23);
        panel_3.add(lblCurrentOsuDirectory);

        currentOsuDirectoryTextField = new JTextField();
        currentOsuDirectoryTextField.setEditable(false);
        currentOsuDirectoryTextField.setHorizontalAlignment(SwingConstants.LEFT);
        currentOsuDirectoryTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        currentOsuDirectoryTextField.setColumns(10);
        currentOsuDirectoryTextField.setBounds(10, 34, 322, 25);
        panel_3.add(currentOsuDirectoryTextField);
        panel_2.setLayout(null);

        JLabel lblRequiresYourOsu = new JLabel(
                "<html>\r\nNote: This feature is not always accurate. If it doesn't work, use the \"Load From File\" button instead to manually load the chart.\r\n<br/>\r\n<br/>\r\n<font color='red'>Warning:</font> Your osu! directory needs to be configured in the Config for this feature to function.\r\n</html>");
        lblRequiresYourOsu.setBounds(6, 11, 326, 75);
        panel_2.add(lblRequiresYourOsu);

        clearLoadCacheButton = new JButton("Clear Load Cache");
        clearLoadCacheButton.setToolTipText(
                "<html>\r\nWhen a chart is loaded using the Auto Chart Loader it will be added to a cache to prevent duplicate loading.\r\n<br/> \r\nIf you want to reload that chart with the Auto Chart Loader, you need to clear the cache.\r\n</html>");
        clearLoadCacheButton.setBounds(6, 165, 125, 23);
        panel_2.add(clearLoadCacheButton);

        removeAllSelectedChartsButton = new JButton("Unload Highlighted Charts");
        removeAllSelectedChartsButton.setBounds(181, 366, 161, 23);
        frame.getContentPane().add(removeAllSelectedChartsButton);

        consoleClearButton = new JButton("Clear Log");
        consoleClearButton.setToolTipText("Clears all of the text in the Log box.");
        consoleClearButton.setBounds(647, 366, 142, 23);
        frame.getContentPane().add(consoleClearButton);

        keepPitchRadioButton = new JRadioButton("Maintain Pitch");
        keepPitchRadioButton.setBounds(550, 366, 91, 23);
        frame.getContentPane().add(keepPitchRadioButton);

        JLabel lblUtility = new JLabel("Utility");
        lblUtility.setBounds(951, 0, 119, 32);
        frame.getContentPane().add(lblUtility);

        UnloadAllChartsAfterWriteRadioButton = new JRadioButton("Unload All Charts After Write");
        UnloadAllChartsAfterWriteRadioButton.setSelected(true);
        UnloadAllChartsAfterWriteRadioButton.setToolTipText("When pressing the \"Write All Charts\" button and this option is enabled, all charts in the Chart view will be unloaded.");
        UnloadAllChartsAfterWriteRadioButton.setBounds(377, 366, 171, 23);
        frame.getContentPane().add(UnloadAllChartsAfterWriteRadioButton);

        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        tabbedPane_2.setBounds(951, 262, 351, 320);
        frame.getContentPane().add(tabbedPane_2);

        panel_5 = new JPanel();
        tabbedPane_2.addTab("Load History", null, panel_5, null);
        panel_5.setLayout(null);

        scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 11, 322, 250);
        panel_5.add(scrollPane_3);

        final DefaultTableModel chartHistoryModel = new DefaultTableModel();

        chartHistoryModel.addColumn("Chart");

        chartHistoryTable = new JTable();
        chartHistoryTable.setFont(new Font("Segoe UI", Font.BOLD, 10));
        chartHistoryTable.setDefaultEditor(Object.class, null);
        chartHistoryTable.setModel(chartHistoryModel);
        chartHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_3.setViewportView(chartHistoryTable);

        reloadChartButton = new JButton("Reload Chart");
        reloadChartButton.setToolTipText("Opens the higlighted chart's directory.");
        reloadChartButton.setBounds(10, 265, 104, 23);
        panel_5.add(reloadChartButton);

        openChartHistoryDirectoryButton = new JButton("Open Chart Directory");
        openChartHistoryDirectoryButton.setToolTipText("Opens the higlighted chart's directory.");
        openChartHistoryDirectoryButton.setBounds(189, 265, 143, 23);
        panel_5.add(openChartHistoryDirectoryButton);

        new Application(this);
    }
}
