package ui;

import javafx.scene.media.AudioClip;
import model.BudgetBuddy;
import model.Category;
import model.categories.Food;
import model.exceptions.InvalidOptionException;
import systemUI.Menu;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class EntryPage {
    private JPanel entryPanel;
    private JLabel title;
    private JLabel enterName;
    private JLabel enterCategory;
    private JLabel enterAmount;
    private JTextField theName;
    private JComboBox comboBox;
    private Category category;
    private JFormattedTextField theAmount;
    private JButton entryBtn;
    private JButton backBtn;

    public EntryPage(BudgetBuddy buddy) {
        JFrame frame2 = new JFrame("New Entry");
        frame2.setContentPane(entryPanel);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setSize(500,400);
        frame2.setLocationRelativeTo(null);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setResizable(false);

        category = new Food();
        entryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = theName.getText();
                    double amount = Double.parseDouble(theAmount.getText());
                    if (amount <= 0) {
                        throw new NumberFormatException();
                    }
                    buddy.createEntry(category, name, amount);
                    playSound("kaching.mp3");
                    new MainPage(buddy);
                    frame2.setVisible(false);
                } catch (NumberFormatException error) {
                    playSound("error.mp3");
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    try {
                        category = Menu.getInstance(buddy).categorySwitch(comboBox.getSelectedIndex()+1);
                    } catch (InvalidOptionException error) {
                        error.printStackTrace();
                    }
                }
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage(buddy);
                frame2.setVisible(false);
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
        comboBox = new JComboBox();
        entryBtn = new JButton();
        backBtn = new JButton();
    }
}
