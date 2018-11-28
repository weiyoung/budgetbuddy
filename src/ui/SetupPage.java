package ui;

import model.BudgetBuddy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SetupPage extends JFrame {
    private BudgetBuddy buddy = BudgetBuddy.getInstance();
    private JPanel setupPanel;
    private JFormattedTextField inputBox;
    private JButton enterButton;
    private JFrame frame;

    public SetupPage() {
        frame = new JFrame("Welcome");
        frame.setContentPane(setupPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.pack();
        setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        enterButton.addActionListener(this::actionPerformed);

    }

    private void actionPerformed(ActionEvent event) {
        String budgetText = inputBox.getText();
        try {
            buddy.setLimit(Double.parseDouble(budgetText));
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException!");
        }
        if (!(buddy.getLimit() > 0)) {
            JOptionPane.showMessageDialog(null, "Please input a valid amount!");
        }
        if (buddy.getLimit() > 0) {
            MainPage mainpg = new MainPage(buddy);
            frame.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new SetupPage();
    }
}
