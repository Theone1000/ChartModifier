package me.darkmans39.chartmodifier.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
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

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        ToolTipManager.sharedInstance().setInitialDelay(100);

        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("unknown.png")));
        frame.setTitle("Oniichan42's Rate Converter");
        frame.setForeground(Color.WHITE);
        frame.setResizable(false);
        frame.setBounds(100, 100, 1318, 601);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 365, 409, 187);
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
        panel.setBounds(10, 24, 409, 305);
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
        lblRatescommaSeparated.setBounds(10, 103, 374, 39);
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
        duplicateButton.setBounds(8, 271, 131, 23);
        panel.add(duplicateButton);

        removeDifficulty = new JButton("Remove Chart");
        removeDifficulty.setBounds(149, 271, 131, 23);
        panel.add(removeDifficulty);

        JLabel lblCurrentDifficulty = new JLabel("Selected Chart");
        lblCurrentDifficulty.setBounds(10, 0, 119, 32);
        frame.getContentPane().add(lblCurrentDifficulty);

        loadFromFileButton = new JButton("Load From File");
        loadFromFileButton.setBounds(10, 336, 161, 23);
        frame.getContentPane().add(loadFromFileButton);

        writeAllDifficultiesButton = new JButton("Write All Difficulties");
        writeAllDifficultiesButton.setBounds(799, 336, 142, 23);
        frame.getContentPane().add(writeAllDifficultiesButton);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(425, 365, 516, 187);
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
        scrollPane_2.setBounds(425, 24, 516, 305);
        frame.getContentPane().add(scrollPane_2);

        consoleLogTextArea = new JTextArea();
        consoleLogTextArea.setLineWrap(true);
        consoleLogTextArea.setFont(new Font("Segoe UI", Font.BOLD, 10));

        consoleLogTextArea.setEditable(false);
        scrollPane_2.setViewportView(consoleLogTextArea);
        consoleLogTextArea.setColumns(10);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        tabbedPane.setBounds(951, 24, 351, 185);
        frame.getContentPane().add(tabbedPane);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Mass Rate Applier", panel_1);
        panel_1.setLayout(null);

        lblNewLabel = new JLabel("Rates (separate each rate by a space)");
        lblNewLabel.setBounds(10, 0, 195, 23);
        panel_1.add(lblNewLabel);

        massApplyRatesButton = new JButton("Duplicate selected charts, then apply rates.");
        massApplyRatesButton.setBounds(10, 123, 326, 23);
        panel_1.add(massApplyRatesButton);

        massApplyRatesTextField = new JTextField();
        massApplyRatesTextField.setHorizontalAlignment(SwingConstants.LEFT);
        massApplyRatesTextField.setFont(new Font("Segoe UI", Font.BOLD, 10));
        massApplyRatesTextField.setColumns(10);
        massApplyRatesTextField.setBounds(10, 24, 195, 25);
        panel_1.add(massApplyRatesTextField);
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Empty", panel_2);
        panel_2.setLayout(null);

        lblShiftOrCtrl = new JLabel("Highlight multiple charts by using shift or control then clicking.");
        lblShiftOrCtrl.setBounds(10, 552, 324, 23);
        frame.getContentPane().add(lblShiftOrCtrl);

        removeAllSelectedChartsButton = new JButton("Remove Selected Charts");
        removeAllSelectedChartsButton.setBounds(181, 336, 161, 23);
        frame.getContentPane().add(removeAllSelectedChartsButton);

        consoleClearButton = new JButton("Clear Log");
        consoleClearButton.setBounds(647, 336, 142, 23);
        frame.getContentPane().add(consoleClearButton);

        new Application(this);
    }
}
