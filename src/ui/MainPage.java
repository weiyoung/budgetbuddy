package ui;

import model.BudgetBuddy;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainPage  {
    private JPanel mainPanel;
    private JLabel welcomeMsg;
    private JTextField dateDisplay;
    private JTextField theTotal;
    private JTextField theLimit;
    private JTextField usedPercentage;
    private JButton addBtn;
    private JButton sumBtn;
    private JButton catBtn;
    private JButton exitBtn;
    private Date date;
    private DecimalFormat df;

    public MainPage(BudgetBuddy buddy) {
        JFrame frame1 = new JFrame("Budget Buddy");
        frame1.setContentPane(mainPanel);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setSize(500,400);
        frame1.setLocationRelativeTo(null);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setResizable(false);

        date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd/M/yyyy");
        dateDisplay.setText(ft.format(date));

        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        theTotal.setText("$" + df.format(buddy.getTotal()));
        theLimit.setText("/$" + df.format(buddy.getLimit()));
        usedPercentage.setText(buddy.getUsedPercentage() + " used");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("performing new entry");
                EntryPage page = new EntryPage(buddy);
                frame1.setVisible(false);
            }
        });
        sumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("performing summary");
            }
        });
        catBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("performing by category");
            }
        });
    }

    private void createUIComponents() {
        dateDisplay = new JTextField();
        dateDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        theTotal = new JTextField();
        theTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        theLimit = new JTextField();
        theLimit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        usedPercentage = new JTextField();
        usedPercentage.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        addBtn = new JButton();
        sumBtn = new JButton();
        catBtn = new JButton();
    }

    public static void main(String[] args) {
        new MainPage(BudgetBuddy.getInstance());
    }



}
