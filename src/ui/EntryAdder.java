package ui;

import model.BudgetBuddy;
import model.exceptions.InvalidOptionException;
import model.exceptions.NegativeInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntryAdder {

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy;
    Menu menu;

    public EntryAdder(BudgetBuddy buddy, Menu menu) {
        this.buddy = buddy;
        this.menu = menu;
    }

    public void addingEntry() {
        String category = "";
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
        checkBudget();
    }

    public double enteringAmount() throws NegativeInputException {
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

    public void checkBudget() {
        if (buddy.getTotal() > buddy.getLimit())
            System.err.printf("LIMIT EXCEEDED BY $%.2f!!! \nPlease be aware of how much you spent!!\n\n", buddy.getTotal() - buddy.getLimit());
        else if (buddy.getTotal() == buddy.getLimit())
            System.err.printf("LIMIT REACHED!!! \nPlease be aware of how much you spent!!\n\n");
        else {
            System.out.printf("You have spent $%.2f so far. \n", buddy.getTotal());
            if (buddy.getTotal()/buddy.getLimit() > 0.9)
                System.out.printf("This is more than 90%% of your budget. \nPlease beware of your spendings.\n\n");
        }
    }


}
