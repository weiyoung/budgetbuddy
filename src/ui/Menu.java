package ui;

import model.BudgetBuddy;
import model.Category;
import model.Saves;
import model.categories.*;
import model.exceptions.InvalidOptionException;
import model.exceptions.NegativeInputException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private BudgetBuddy buddy;
    private EntryAdder a;
    private SummaryChecker s;
    private static Menu thisMenu = null;

    private Menu(BudgetBuddy buddy) {
        this.buddy = buddy;
        a = EntryAdder.getInstance(buddy, this);
        s = SummaryChecker.getInstance(buddy, this);
    }

    public static Menu getInstance(BudgetBuddy buddy) {
        if (thisMenu == null)
            thisMenu = new Menu(buddy);
        return thisMenu;
    }

    protected void menu() {
        System.out.println("\n-------------MENU------------- "
                + "\n1 - Add new entry "
                + "\n2 - View monthly summary "
                + "\n3 - Check summary by category "
                + "\n4 - Exit ");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        } finally {
            if (option==1) a.addingEntry();
            else if (option==2) s.monthlySummary();
            else if (option==3) s.summaryByCategory();
            else if (option==4) exit();
            else System.out.println("Invalid option.");
            menu();
        }
    }

    protected int choosingCategory() {
        int option = 0;
        System.out.println("\nCHOOSE CATEGORY: " +
                "\n1 - Food" +
                "\n2 - Groceries" +
                "\n3 - Entertainment" +
                "\n4 - Bills" +
                "\n5 - Utilities" +
                "\n6 - Rent" +
                "\n7 - Miscellaneous");
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return option;
    }

    protected void checkInputValidity(double amount) throws NegativeInputException {
        if (amount <= 0)
            throw new NegativeInputException("Invalid - please input positive amount.");
    }

    protected Category categorySwitch(int option) throws InvalidOptionException {
        switch (option) {
            case 1: return new Food();
            case 2: return new Groceries();
            case 3: return new Entertainment();
            case 4: return new Bills();
            case 5: return new Utilities();
            case 6: return new Rent();
            case 7: return new Miscellaneous();
            default: throw new InvalidOptionException("Invalid option.");
        }
    }

    protected void checkBudget() {
        System.out.printf("Your monthly budget is $%.2f\n", buddy.getLimit());
        if (buddy.getTotal() > buddy.getLimit())
            System.out.printf("LIMIT EXCEEDED BY $%.2f!!!\n(｡>﹏<｡) \nPlease be aware of how much you spent!!\n\n", buddy.getTotal() - buddy.getLimit());
        else if (buddy.getTotal() == buddy.getLimit())
            System.out.printf("LIMIT REACHED!!!\n(｡>﹏<｡) \nPlease be aware of how much you spent!!\n\n");
        else {
            System.out.printf("You have spent $%.2f so far. \n\n", buddy.getTotal());
            if (buddy.getTotal()/buddy.getLimit() > 0.9)
                System.out.printf("This is more than 90%% of your budget.\n(ಠ__ಠ) \nPlease be aware of how much you spent.\n\n");
        }
    }

    private void exit() {
        try {
            Saves s = new Saves();
            s.save(buddy);
            System.out.println("Byeee ヽ(^o^)丿");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.err.println("Error: save file not found.");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error: encoding unsupported.");
        }
    }

}
