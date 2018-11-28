package ui;

import model.BudgetBuddy;
import model.Category;
import model.exceptions.*;
import observer.BudgetObserver;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntryAdder implements BudgetObserver {

    private Scanner scanner = new Scanner(System.in);
    private BudgetBuddy buddy;
    private Menu menu;
    private static EntryAdder thisEntryAdder = null;

    private EntryAdder(BudgetBuddy buddy, Menu menu) {
        this.buddy = buddy;
        this.menu = menu;
    }

    public static EntryAdder getInstance(BudgetBuddy buddy, Menu menu) {
        if (thisEntryAdder == null)
            thisEntryAdder = new EntryAdder(buddy, menu);
        return thisEntryAdder;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    protected void addingEntry() {
        Category category = null;
        try {
            int option = menu.choosingCategory();
            category = menu.categorySwitch(option);
        } catch (InvalidOptionException e) {
            System.out.println("Invalid option.");
            menu.menu();
        }
        System.out.println("What would you like to name this entry? \n(press ENTER when done)");
        String name = scanner.nextLine();
        double amount = 0;
        try {
            amount = enteringAmount();
        } catch (NegativeInputException e) {
            System.out.println(e.getMessage());
        }
        buddy.createEntry(category, name, amount);
        menu.checkBudget();
        System.out.println(buddy.getUsedPercentage());
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    protected double enteringAmount() throws NegativeInputException {
        System.out.println("How much did you spend on it?");
        double amount = 0;
        try {
            amount = scanner.nextDouble();
            menu.checkInputValidity(amount);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
        return amount;
    }

    @Override
    public void update(int amount) {
        System.out.println("Observer says: number of entries: " + amount);
    }
}
