package ui;

import model.BudgetBuddy;
import model.Saves;

public class App {
    private BudgetBuddy buddy;


    public App() {
        buddy = BudgetBuddy.getInstance();
        try {
            Saves s = new Saves();
            s.load(buddy);
            System.out.println("successfully loaded...");
            new MainPage(buddy);
        } catch (Exception e) {
            System.out.println("save file unavailable...");
            new SetupPage(buddy);
        }
    }


    public static void main(String[] args) {
        new App();
    }

}
