package ui;

import model.BudgetBuddy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SetupPage extends JFrame {
    private BudgetBuddy buddy = BudgetBuddy.getInstance();
    private JPanel setupPanel;
    private JFormattedTextField inputBox;
    private JButton enterButton;
    private JFrame frame0;

    public SetupPage() {
        frame0 = new JFrame("Welcome");
        frame0.setContentPane(setupPanel);
        frame0.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame0.setSize(500,400);
        frame0.setLocationRelativeTo(null);
        frame0.pack();
        setLocationRelativeTo(null);
        frame0.setVisible(true);
        frame0.setResizable(false);

        enterButton.addActionListener(this::actionPerformed);

    }

    private void actionPerformed(ActionEvent e) {
        String budgetText = inputBox.getText();
        try {
            buddy.setLimit(Double.parseDouble(budgetText));
        } catch (NumberFormatException error) {
            System.err.println("NumberFormatException!");
        }
        if (!(buddy.getLimit() > 0)) {
            JOptionPane.showMessageDialog(null, "Please input a valid amount!");
        }
        if (buddy.getLimit() > 0) {
            MainPage mainpg = new MainPage(buddy);
            frame0.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new SetupPage();
    }
}
