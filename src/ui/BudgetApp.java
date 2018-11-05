package ui;

import model.BudgetBuddy;
import model.Category;
import model.exceptions.InvalidChoiceException;
import model.exceptions.NegativeBudgetException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetApp {

    public static void main(String[] args) {
        new BudgetApp();
    }

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy0 = new BudgetBuddy();

    BudgetApp() {
        System.out.println("Nice to meet you! I am your Budget Buddy :) \n");
        start();
        menu();
    }

    public void start() {
        try {
            buddy0.load();
        } catch (Exception e) {
            System.out.println("What is your monthly budget?");
            try {
                inputMonthlyBudget();
            } catch (NegativeBudgetException e1) {
                System.out.println(e1.getMessage());
            }
            System.out.printf("Confirm monthly budget at $%.2f? (enter Y or N)", buddy0.getLimit());
            String input = scanner.next();
            if (input.equalsIgnoreCase("y"))
                System.out.printf("Monthly budget set at $%.2f \n", buddy0.getLimit());
            else {
                System.out.println("\nLet's start again. ");
                start();
            }
       }
    }

    public void inputMonthlyBudget() throws NegativeBudgetException {
        try {
            double budget = scanner.nextDouble();
            if (budget < 0)
                throw new NegativeBudgetException("Budget cannot be negative!");
            buddy0.setLimit(budget);
        } catch (InputMismatchException e) {
            System.out.println("invalid input amount");
            scanner.nextLine();
        }
    }

    public void menu() {
        System.out.println("\n----------MENU---------- \n1 - Add new entry \n2 - View monthly summary \n3 - Check summary by category \n4 - Exit");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        } finally {
            if (option==1) {
                addingEntry();
            } else if (option==2) {
                viewingSummary();
            } else if (option==3) {
                checkingByCategory();
            } else if (option==4) {
                exit();
            } else {
                System.out.println("invalid option");
                menu();
            }
        }
    }

    public void addingEntry() {
        Category category = null;
        try {
            int choice = choosingCategory();
            category  = buddy0.categorySwitch(choice);
        } catch (InvalidChoiceException e) {
            System.out.println("invalid option");
            addingEntry();
        }
        System.out.println("What would you like to name this entry? \n(press ENTER when done)");
        String entryName = scanner.nextLine();
        double entryAmount = enteringAmount();
        buddy0.createNewEntry(category, entryName, entryAmount);
        menu();
    }

    public void viewingSummary() {
        buddy0.viewSummary();
        menu();
    }

    public void checkingByCategory() {
        int choice = choosingCategory();
        Category category  = buddy0.categorySwitch(choice);
        buddy0.checkSummaryByCategory(category);
        menu();
    }

    public int choosingCategory() {
        System.out.println("\nCHOOSE CATEGORY: \n1 - Food \n2 - Groceries \n3 - Entertainment " +
                "\n4 - Bills \n5 - Utilities \n6 - Rent \n7 - Miscellaneous");
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("invalid option");
            addingEntry();
        } finally {
            scanner.nextLine();
        }
        return choice;
    }

    public double enteringAmount() {
        System.out.println("How much did you spend on it?");
        double entryAmount = 0;
        try {
            entryAmount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("invalid input amount");
        }
        return entryAmount;
    }

    public void exit() {
        try {
            buddy0.save();
        } catch (FileNotFoundException e) {
            System.out.println("Error 404: Save file not found.");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error 404: Encoding unsupported.");
        }
//        } finally {
//            System.out.println("\nBye~");
//        }
    }


}
