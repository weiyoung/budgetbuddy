package model;

import java.util.ArrayList;

public class BudgetBuddy {

    private double total;
    private double limit;
    ArrayList<Entry> entries;

    public BudgetBuddy() {
        total = 0;
        limit = 0;
        entries = new ArrayList();
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getLimit() {
        return this.limit;
    }

    public double getTotal() {
        return this.total;
    }

    public void addEntry(int choice, String name, double entryAmount) {
        Category category;
        switch (choice) {
            case 1: category = new EatOut(); break;
            case 2: category = new Groceries(); break;
            case 3: category = new Entertainment(); break;
            case 4: category = new Bills(); break;
            case 5: category = new Utilities(); break;
            case 6: category = new Rent(); break;
            default: category = new Miscellaneous(); break;
        }
        Entry entry = new Entry(category, name, entryAmount);
        entries.add(entry);
        checkBudget(entry);
    }

    public void checkBudget(Entry entry) {
        total += entry.entryAmount;
        if (getTotal() > getLimit())
            System.out.printf("You have exceeded your limit by $%.2f!! \nPlease be aware of how much you spent!!\n\n", getTotal() - getLimit());
        else {
            System.out.printf("You have spent $%.2f so far. \n", getTotal());
            //what happens after you overspend?
        }
    }

    public void viewSummary() {

    }





}
