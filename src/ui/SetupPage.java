package ui;

import javafx.scene.media.AudioClip;
import model.BudgetBuddy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SetupPage extends JFrame {
    private BudgetBuddy buddy;
    private JFrame frame0;
    private JPanel setupPanel;
    private JFormattedTextField inputBox;
    private JButton enterButton;

    public SetupPage(BudgetBuddy buddy) {
        playSound("nokia.mp3");
        this.buddy = buddy;
        System.out.println("Buddy ID: " + System.identityHashCode(buddy));

        frame0 = new JFrame("Welcome");
        frame0.setContentPane(setupPanel);
        frame0.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame0.setSize(500,400);
        frame0.setLocationRelativeTo(null);
        frame0.pack();
        setLocationRelativeTo(null);
        frame0.setVisible(true);
        frame0.setResizable(false);

        enterButton.addActionListener(this::actionPerformed);
    }

    private void actionPerformed(ActionEvent e) {
        String text = inputBox.getText();
        try {
            buddy.setLimit(Double.parseDouble(text));
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }
        if (!(buddy.getLimit() > 0)) {
            playSound("error.mp3");
            JOptionPane.showMessageDialog(null, "Please input a valid amount!");
        }
        if (buddy.getLimit() > 0) {
            new MainPage(buddy);
            frame0.setVisible(false);
        }
    }

    private void playSound(String path){
        try {
            AudioClip clip = new AudioClip(this.getClass().getResource(path).toString());
            clip.play();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing music.");
        }
    }
}
