package ui;

import model.BudgetBuddy;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainPage  {
    private JPanel mainPanel;
    private JTextField theTotal;
    private JTextField theLimit;
    private JButton addBtn;
    private JButton sumBtn;
    private JButton catBtn;
    private JTextField usedPercentage;
    private JLabel welcomeBack;
    private JTextField dateDisplay;

    public MainPage(BudgetBuddy buddy) {
        JFrame frame = new JFrame("Budget Buddy");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);



        System.out.println(buddy.getLimit());

    }

    private void actionPerformed(ActionEvent event) {

    }


    public static void main(String[] args) {
        new MainPage(BudgetBuddy.getInstance());
    }



}
