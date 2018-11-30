package ui;

import model.BudgetBuddy;
import model.Entry;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SummaryPage {
    private JPanel summaryPanel;
    private JLabel summaryHead;
    private JTextField theUsed;
    private JTextField theBudget;
    private JTextField buddySays;
    private JTextArea sumArea;
    private JScrollPane sumPrinter;
    private JButton backBtn;
    private DecimalFormat df;

    public SummaryPage(BudgetBuddy buddy) {
        JFrame frame2 = new JFrame("View Summary");
        frame2.setContentPane(summaryPanel);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(500,400);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setResizable(false);

        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        theUsed.setText("used: $" + df.format(buddy.getTotal()));
        theBudget.setText("budget: $" + df.format(buddy.getLimit()));
        buddySays.setText(checkBudget(buddy));

        String string = "";
        for (Entry e: buddy.getEntries()) {
            String entry = " Name: " + e.getName() + "\n Category: " + e.getCategory() + "\n Spent: $" + df.format(e.getAmount()) + "\n\n";
            string += entry;
        }
        string += " Number of entries: " + buddy.getEntries().size();
        sumArea.setText(string);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(buddy);
                frame2.setVisible(false);
            }
        });
    }

    public String checkBudget(BudgetBuddy buddy) {
        if (buddy.getTotal() > buddy.getLimit())
            return "Budget exceeded by $" + df.format(buddy.getTotal()-buddy.getLimit()) + "! OMG why you overspend :(";
        else if (buddy.getTotal()/buddy.getLimit() > 0.9)
            return "That's " + buddy.getUsedPercentage() + " of your budget. Please watch your spendings :o";
        else
            return "That's " + buddy.getUsedPercentage() + " of your budget. ";
    }

    private void createUIComponents() {
        theUsed = new JTextField();
        theUsed.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        theBudget = new JTextField();
        theBudget.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        buddySays = new JTextField();
        buddySays.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        sumArea = new JTextArea();
        sumPrinter = new JScrollPane();
        sumPrinter.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        backBtn = new JButton();
    }
}
