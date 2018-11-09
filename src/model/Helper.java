package model;

import model.categories.*;
import model.exceptions.InvalidChoiceException;
import model.exceptions.Negative.NegativeInputException;

import java.util.List;

public class Helper {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void categoryScreen() {
        println("\nCHOOSE CATEGORY: " +
                "\n1 - Food " +
                "\n2 - Groceries " +
                "\n3 - Entertainment " +
                "\n4 - Bills " +
                "\n5 - Utilities " +
                "\n6 - Rent " +
                "\n7 - Miscellaneous");
    }

    public static void menuScreen() {
        println("\n----------MENU---------- "
                + "\n1 - Add new entry "
                + "\n2 - View monthly summary "
                + "\n3 - Check summary by category "
                + "\n4 - Exit ");
    }

    public static void invalidize() {
        invalidize(" ");
    }

    public static void invalidize(String error) {
        System.err.println("Invalid " + error);
    }

    public static Category categorySwitch(int choice) throws InvalidChoiceException {
        Category category;
        switch (choice) {
            case 1: category = new Food(); break;
            case 2: category = new Groceries(); break;
            case 3: category = new Entertainment(); break;
            case 4: category = new Bills(); break;
            case 5: category = new Utilities(); break;
            case 6: category = new Rent(); break;
            case 7: category = new Miscellaneous(); break;
            default: throw new InvalidChoiceException();
        }
        return category;
    }

    public static void checkEntryValidity(double amount) throws NegativeInputException {
        if (amount <= 0)
            throw new NegativeInputException("invalid - please input a positive amount.");
    }

    public static boolean isSaveFileEmpty(List<String> lines) {
        boolean empty = false;
        for (String s: lines )
            if (s.isEmpty())
                empty = true;
        return empty;
    }

}
