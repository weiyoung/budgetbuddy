package ui;

import model.BudgetBuddy;
import model.Entry;
import model.Saves;
import model.exceptions.InvalidOptionException;
import model.exceptions.NegativeInputException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    BudgetBuddy buddy;

    public Menu(BudgetBuddy buddy) {
        this.buddy = buddy;
    }

    protected void menu() {
        System.out.println("\n--------------MENU-------------- "
                + "\n1 - Add new entry "
                + "\n2 - View monthly showSummary "
                + "\n3 - Check showSummary by category "
                + "\n4 - Exit ");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        } finally {
            if (option==1) {
                EntryAdder a = new EntryAdder(buddy, this);
                a.addingEntry();
            } else if (option==2) monthlySummary();
            else if (option==3) summaryByCategory();
            else if (option==4) exit();
            else System.out.println("Invalid option.");
            menu();
        }
    }

    public int choosingCategory() {
        int option = 0;
        System.out.println("\nCHOOSE CATEGORY: " +
                "\n1 - Food " +
                "\n2 - Groceries " +
                "\n3 - Entertainment " +
                "\n4 - Bills " +
                "\n5 - Utilities " +
                "\n6 - Rent " +
                "\n7 - Miscellaneous");
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return option;
    }

    public void checkInputValidity(double amount) throws NegativeInputException {
        if (amount <= 0)
            throw new NegativeInputException("Invalid - please input positive amount.");
    }

    public String categorySwitch(int option) throws InvalidOptionException {
        switch (option) {
            case 1: return "Food";
            case 2: return "Groceries";
            case 3: return "Entertainment";
            case 4: return "Bills";
            case 5: return "Utilities";
            case 6: return "Rent";
            case 7: return "Miscellaneous";
            default: throw new InvalidOptionException("Invalid option.");
        }
    }

    private void monthlySummary() {
        for (Entry e: buddy.getEntries()) {
            System.out.printf("Category: %s \n", e.getCategory());
            System.out.printf("Tag: %s \n", e.getName());
            System.out.printf("Amount: $%.2f \n\n", e.getAmount());
        }
    }

    private void summaryByCategory() {
        String category = "";
        try {
            int option = choosingCategory();
            category = categorySwitch(option);
        } catch (InvalidOptionException e) {
            System.out.println("Invalid option.");
        }
        viewSummaryByCategory(category);
    }

    private void viewSummaryByCategory(String category) {
        int count = 0;
        for (Entry e : buddy.getEntries()) {
            if (e.equals(buddy.categoryMap.get(category))) {
                System.out.println("(" + category + ")");
                System.out.printf("Tag: %s \n", e.getName());
                System.out.printf("Amount: $%.2f \n\n", e.getAmount());
            } else
                count++;
        }
        if (count == buddy.getEntries().size())
            System.out.println("No entries recorded.");
    }

    private void exit() {
        try {
            Saves s = new Saves();
            s.save(buddy);
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.err.println("Error: save file not found.");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error: encoding unsupported.");
        }
    }





}
