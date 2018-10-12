package ui;

import model.BudgetBuddy;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BudgetBuddy buddy0 = new BudgetBuddy();
        System.out.println("Nice to meet you! I am your Budget Buddy :) \n");

        while (true) {
            System.out.println("What is your monthly budget?");
            double budget = scanner.nextDouble();
            buddy0.setLimit(budget);
            System.out.printf("Confirm monthly budget at $%.2f? (enter Y or N)", buddy0.getLimit());
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                System.out.printf("Monthly budget set at $%.2f \n", buddy0.getLimit());
                break;
            } else
                System.out.println("\nLet's start again. ");
        }

        boolean rebootMenu = false, addEntry = false, viewSummary = false;

        do {
            System.out.println("\n----------MENU---------- \n1 - Add new entry \n2 - View monthly summary ");
            int option = scanner.nextInt();
            if (option==1) {
                addEntry = true;
                rebootMenu = false;
            } else if (option==2) {
                viewSummary = true;
                rebootMenu = false;
            } else {
                System.out.println("invalid option");
                rebootMenu = true;
            }
        } while (rebootMenu);

        while (addEntry) {
            System.out.println("\nCHOOSE CATEGORY: \n1 - Eat Out \n2 - Groceries \n3 - Entertainment " +
                    "\n4 - Bills \n5 - Utilities \n6 - Rent \n7 - Miscellaneous");
            int choice = scanner.nextInt();
            System.out.println("What would you like to name this entry? \n(press ENTER when done)");
            String entryName = scanner.nextLine();
            System.out.println("How much did you spend on it?");
            double entryAmount = scanner.nextDouble();
            buddy0.createNewEntry(choice, entryName, entryAmount);
            buddy0.viewSummary();
            //testing:
            addEntry = false;
            //run the option menu again
        }

        buddy0.save();
        buddy0.load();
        //when exit,

    }


}
