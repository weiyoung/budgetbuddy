package ui;

import model.BudgetBuddy;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class EntryPage {
    private JPanel entryPanel;
    private JComboBox comboBox;
    private JFormattedTextField formattedTextField1;
    private JButton entryBtn;
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

        entryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(buddy);
                frame2.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new EntryPage(BudgetBuddy.getInstance());
    }

    private void createUIComponents() {
        entryBtn = new JButton();
    }
}
