package ui;

import model.BudgetBuddy;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class EntryPage {
    private JPanel entryPanel;
    private JComboBox comboBox1;
    private JFormattedTextField formattedTextField1;
    private JButton addEntryButton;
    private JTextField textField1;
    private JLabel title;
    private JLabel enterName;
    private JLabel enterCategory;
    private JLabel enterAmount;

    public EntryPage(BudgetBuddy buddy) {
        JFrame frame2 = new JFrame("New Entry");
        frame2.setContentPane(entryPanel);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(500,400);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setResizable(false);


    }

    public static void main(String[] args) {
        new EntryPage(BudgetBuddy.getInstance());
    }

}
