package ui;

import model.BudgetBuddy;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CategoryPage {
    private JPanel categoryPanel;
    private JLabel summaryHead;
    private JLabel enterCategory;
    private JComboBox comboBox;
    private JScrollPane sumPrinter;
    private JButton backBtn;

    public CategoryPage(BudgetBuddy buddy) {
        JFrame frame2 = new JFrame("Summary by Category");
        frame2.setContentPane(categoryPanel);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(500,400);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setResizable(false);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(buddy);
                frame2.setVisible(false);
            }
        });

    }

    private void createUIComponents() {
        sumPrinter = new JScrollPane();
        sumPrinter.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        backBtn = new JButton();
    }

    public static void main(String[] args) {
        new CategoryPage(BudgetBuddy.getInstance());
    }
}
