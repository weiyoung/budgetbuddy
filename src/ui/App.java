package ui;

import model.BudgetBuddy;
import model.Category;
import model.exceptions.InvalidChoiceException;
import model.exceptions.Negative.NegativeInputException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static model.Helper.*;

public class App {

    public static void main(String[] args) {
        new App();
    }

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy = new BudgetBuddy();

    App() {
        println("Nice to meet you! I am your Budget Buddy :) \n");
        start();
        menu();
    }

    public void start() {
        try {
            buddy.load();
        } catch (Exception e1) {
            println("What is your monthly budget?");
            try {
                inputMonthlyBudget();
            } catch (NegativeInputException e2) {
                println(e2.getMessage());
            }
            printf("Confirm monthly budget at $%.2f? (enter Y or N)", buddy.getLimit());
            String input = scanner.next();
            if (input.equalsIgnoreCase("y"))
                printf("Monthly budget set at $%.2f \n", buddy.getLimit());
            else {
                println("\nLet's start again. ");
                start();
            }
        }
    }

    public void inputMonthlyBudget() throws NegativeInputException {
        try {
            double budget = scanner.nextDouble();
            checkEntryValidity(budget);
            buddy.setLimit(budget);
        } catch (InputMismatchException e) {
            invalidize("input");
            scanner.nextLine();
        }
    }

    public void menu() {
        menuScreen();
        int op = 0;
        try {
            op = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        } finally {
            if (op==1) addingEntry();
            else if (op==2) viewingSummary();
            else if (op==3) checkingByCategory();
            else if (op==4) exit();
            else invalidize("option");
            menu();
        }
    }

    public void addingEntry() {
        Category category = null;
        try {
            int choice = choosingCategory();
            category = categorySwitch(choice);
        } catch (InvalidChoiceException e) {
            invalidize("option");
            addingEntry();
        }
        println("What would you like to name this entry? \n(press ENTER when done)");
        String entryName = scanner.nextLine();
        double entryAmount = 0;
        try {
            entryAmount = enteringAmount();
        } catch (NegativeInputException e) {
            println(e.getMessage());
        }
        buddy.createNewEntry(category, entryName, entryAmount);
    }

    public void viewingSummary() {
        buddy.viewSummary();
    }

    public void checkingByCategory() {
        int choice = choosingCategory();
        Category category  = categorySwitch(choice);
        buddy.checkSummaryByCategory(category);
    }

    public int choosingCategory() {
        categoryScreen();
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            invalidize("option");
            addingEntry();
        } finally {
            scanner.nextLine();
        }
        return choice;
    }

    public double enteringAmount() throws NegativeInputException{
        println("How much did you spend on it?");
        double entryAmount = 0;
        try {
            entryAmount = scanner.nextDouble();
            checkEntryValidity(entryAmount);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            invalidize("input");
        }
        return entryAmount;
    }

    public void exit() {
        try {
            buddy.save();
        } catch (FileNotFoundException e) {
            println("Error 404: Save file not found.");
        } catch (UnsupportedEncodingException e) {
            println("Error 404: Encoding unsupported.");
        }
    }

}
