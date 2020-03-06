package me.darkmans39.chartmodifier.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

    public JTable getChartTable() {
        return chartTable;
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setForeground(Color.WHITE);
        frame.setResizable(false);
        frame.setBounds(100, 100, 772, 569);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 365, 577, 164);
        frame.getContentPane().add(scrollPane);

        final DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Charts");
        // model.addRow(new String[] { "Cyber Induction ", "[IcyWorld's Lv.15 1.0x]" });
        // model.addRow(new String[] { "Cyber Induction ", "[IcyWorld's Lv.15 1.0x]" });

        chartTable = new JTable();
        chartTable.setDefaultEditor(Object.class, null);
        chartTable.setModel(model);
        // table.getColumn("Charts").setPreferredWidth(160);
        chartTable.setFont(new Font("Segoe UI", Font.BOLD, 10));
        scrollPane.setViewportView(chartTable);

        JLabel lblCurrentDifficulties = new JLabel("All Difficulties");
        lblCurrentDifficulties.setBounds(512, 340, 75, 14);
        frame.getContentPane().add(lblCurrentDifficulties);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        panel.setBounds(10, 24, 746, 305);
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

        JLabel lblRatescommaSeparated = new JLabel("Rates (Semicolon separated list)");
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

        JLabel lblCurrentDifficulty = new JLabel("Selected Difficulty");
        lblCurrentDifficulty.setBounds(10, 0, 119, 25);
        frame.getContentPane().add(lblCurrentDifficulty);

        loadFromFileButton = new JButton("Load From File");
        loadFromFileButton.setBounds(10, 336, 191, 23);
        frame.getContentPane().add(loadFromFileButton);

        new Application(this);
    }
}
