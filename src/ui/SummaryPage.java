package ui;

import model.BudgetBuddy;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SummaryPage {
    private JPanel summaryPanel;
    private JLabel summaryHead;
    private JTextField theUsed;
    private JTextField theBudget;
    private JTextField buddySays;
    private JScrollPane sumPrinter;
    private JButton backBtn;

    public SummaryPage(BudgetBuddy buddy) {
        JFrame frame2 = new JFrame("View Summary");
        frame2.setContentPane(summaryPanel);
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

    public static void main(String[] args) {
        new SummaryPage(BudgetBuddy.getInstance());
    }


    private void createUIComponents() {
        theUsed = new JTextField();
        theUsed.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        theBudget = new JTextField();
        theBudget.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        buddySays = new JTextField();
        buddySays.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        sumPrinter = new JScrollPane();
        sumPrinter.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        backBtn = new JButton();
    }
}
