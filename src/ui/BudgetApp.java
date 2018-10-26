package ui;

import model.BudgetBuddy;
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
        System.out.println("\n----------MENU---------- \n1 - Add new entry \n2 - View monthly summary \n3 - Exit");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        } finally {
            if (option==1) {
                addingEntry();
            } else if (option==2) {
                buddy0.viewSummary();
                menu();
            } else if (option==3) {
                exit();
            } else {
                System.out.println("invalid option");
                menu();
            }
        }
    }

    public void addingEntry() {
        int choice = choosingCategory();
        System.out.println("What would you like to name this entry? \n(press ENTER when done)");
        String entryName = scanner.nextLine();
        double entryAmount = enteringAmount();
        buddy0.createNewEntry(choice, entryName, entryAmount);
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
