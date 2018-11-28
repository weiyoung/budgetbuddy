package ui;

import model.BudgetBuddy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Setup extends JFrame implements ActionListener {
    private BudgetBuddy buddy = BudgetBuddy.getInstance();
    private Menu menu = Menu.getInstance(buddy);
    private JLabel welcomeMsg;
    private JTextField budgetEntry;

    public Setup() {
        super("Welcome");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(100, 25, 100, 25) );
        setLayout(new FlowLayout());

        welcomeMsg = new JLabel("<html>Nice to meet you!!!<br/><br/>I am your Budget Buddy (^ ω ^)<br/></html>");
        budgetEntry = new JFormattedTextField(NumberFormat.getNumberInstance());

        JButton btn = new JButton("Enter");
        btn.setActionCommand("enterBudget");
        btn.addActionListener(this);

        add(welcomeMsg);
        add(budgetEntry);
        add(btn);
        pack();
//        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("enterBudget")) {
            String budgetText = budgetEntry.getText();
            buddy.setLimit(Double.parseDouble(budgetText));
        }
    }
    public static void main(String[] args) {
        new Setup();
    }
}