package ui;

import javafx.scene.media.AudioClip;
import model.BudgetBuddy;
import model.Saves;
import model.categories.Food;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainPage  {
    private JFrame frame1;
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
        frame1 = new JFrame("Budget Buddy");
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
        theLimit.setText("/ $" + df.format(buddy.getLimit()));
        usedPercentage.setText(buddy.getUsedPercentage() + " used");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EntryPage(buddy);
                frame1.setVisible(false);
            }
        });
        sumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SummaryPage(buddy);
                frame1.setVisible(false);
            }
        });
        catBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CategoryPage(buddy, new Food());
                frame1.setVisible(false);
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("bye.mp3");
                try {
                    Saves s = new Saves();
                    s.save(buddy);
                    System.out.println("Saved successfully ヽ(^o^)丿");
                } catch (FileNotFoundException error) {
                    System.err.println("Error: save file not found.");
                } catch (UnsupportedEncodingException error) {
                    System.err.println("Error: encoding unsupported.");
                } finally {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
            }
        });
    }

    private void playSound(String path){
        try {
            AudioClip clip = new AudioClip(this.getClass().getResource(path).toString());
            clip.play();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing music.");
        }
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
}
