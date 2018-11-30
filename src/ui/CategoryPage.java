package ui;

import model.BudgetBuddy;
import model.Category;
import model.Entry;
import model.exceptions.InvalidOptionException;
import systemUI.Menu;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CategoryPage {
    private JPanel categoryPanel;
    private JLabel summaryHead;
    private JLabel enterCategory;
    private JComboBox comboBox;
    private JTextArea sumArea;
    private JScrollPane sumPrinter;
    private JButton backBtn;
    private DecimalFormat df;

    public CategoryPage(BudgetBuddy buddy, Category category) {
        JFrame frame2 = new JFrame("Summary by Category");
        frame2.setContentPane(categoryPanel);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(500,400);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setResizable(false);

        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    try {
                        Category newCat = Menu.getInstance(buddy).categorySwitch(comboBox.getSelectedIndex());
                        new CategoryPage(buddy, newCat);
                        frame2.setVisible(false);
                    } catch (InvalidOptionException error) {
                        error.printStackTrace();
                    }
                }
            }
        });

        String string = " <" + category.getName() + ">\n\n";
        String entry = "";
        int count = 0;
        for (Entry e: buddy.getEntries()) {
            if (e.getCategory().equals(category.getName())) {
                entry = " Name: " + e.getName() + "\n Spent: $" + df.format(e.getAmount()) + "\n\n";
                string += entry;
            } else
                count++;
        }
        if (buddy.getEntries() == null || count == buddy.getEntries().size())
            string += " No entries recorded";
        sumArea.setText(string);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(buddy);
                frame2.setVisible(false);
            }
        });
    }

    private void createUIComponents() {
        sumArea = new JTextArea();
        sumPrinter = new JScrollPane();
        sumPrinter.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        backBtn = new JButton();
    }
}
