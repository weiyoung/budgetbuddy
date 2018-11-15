package ui;

import model.BudgetBuddy;
import model.Saves;
import model.exceptions.NegativeInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        new App();
    }

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy = new BudgetBuddy();
    Menu menu = new Menu(buddy);

    App() {
        System.out.println("Hi! I am your Budget Buddy :)");
        start();
        menu.menu();
    }

    private void start() {
        try {
            Saves s = new Saves();
            s.load(buddy);
        } catch (Exception ex) {
            System.out.println("Enter monthly budget: ");
            try {
                inputBudget();
            } catch (NegativeInputException e) {
                System.out.println(e.getMessage());
            }
            System.out.printf("Confirm monthly budget at $%.2f? (enter Y or N)", buddy.getLimit());
            String input = scanner.next();
            if (input.equalsIgnoreCase("y"))
                System.out.printf("Monthly budget set at $%.2f \n", buddy.getLimit());
            else {
                System.out.printf("\nLet's start again. ");
                start();
            }
        }
    }

    private void inputBudget() throws NegativeInputException {
        try {
            double budget = scanner.nextDouble();
            menu.checkInputValidity(budget);
            buddy.setLimit(budget);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

}
