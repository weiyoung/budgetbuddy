package ui;

import javafx.scene.media.AudioClip;
import model.BudgetBuddy;
import model.Saves;

import javax.swing.*;

public class BudgetApp {
    private BudgetBuddy buddy;

    public BudgetApp() {
        buddy = BudgetBuddy.getInstance();
        try {
            Saves s = new Saves();
            s.load(buddy);
            System.out.println("successfully loaded...");
            new MainPage(buddy);
            playSound("hello.mp3");
        } catch (Exception e) {
            System.out.println("save file unavailable...");
            new SetupPage(buddy);
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

    public static void main(String[] args) {
        new BudgetApp();
    }
}
