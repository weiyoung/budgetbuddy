package ui;

import model.BudgetBuddy;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BudgetBuddy buddy0 = new BudgetBuddy();
        System.out.println("Nice to meet you! \nI am your Budget Buddy :) ");
        boolean setBudget = true;
        while (setBudget) {
            System.out.println("What is your monthly budget?");
            double budget = scanner.nextDouble();
            buddy0.setLimit(budget);
            System.out.printf("Do you want to set your monthly budget at $%.2f? (enter Y or N)", buddy0.getLimit());
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                System.out.printf("Your monthly budget is set at $%.2f \n", buddy0.getLimit());
                setBudget = false;
                break;
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("Let's start again. ");
            } else
                System.out.println("Invalid input. ");
        }
        boolean addEntry = false, viewSummary = false, runLoop = false;

        do {
            System.out.println("What would you like to do? \n1 - Add new entry \n2 - View monthly summary ");
            int option = scanner.nextInt();
            if (option==1)
                addEntry = true;
            else if (option==2)
                viewSummary = true;
            else {
                System.out.println("invalid option");
                runLoop = true;
            }
        } while (runLoop);

        while (addEntry) {
            System.out.println("Choose a category: \n1 - Eat Out \n2 - Groceries \n3 - Entertainment " +
                    "\n4 - Bills \n5 - Utilities \n6 - Rent \n7 - Miscellaneous");
            int choice = scanner.nextInt();
            System.out.println("What would you like to name this entry? \n(press ENTER when done)");
            String entryName = scanner.nextLine();
            scanner.nextLine();
            System.out.println("How much did you spend on it?");
            double entryAmount = scanner.nextDouble();
            buddy0.addEntry(choice, entryName, entryAmount);
            //run the option menu again
        }

    }


}
