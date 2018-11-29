package model;

import observer.Subject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BudgetBuddy extends Subject {

    private double total;
    private double limit;
    private ArrayList<Entry> entries;
    private Map<Category, Entry> categoryMap = new HashMap<>();
    private static BudgetBuddy thisBuddy = null;

    private BudgetBuddy() {
        total = 0;
        limit = 0;
        entries = new ArrayList();
    }

    public static BudgetBuddy getInstance() {
        if (thisBuddy == null)
            thisBuddy = new BudgetBuddy();
        return thisBuddy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        if (limit > 0)
            this.limit = limit;
        else
            this.limit = 0;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void createEntry(Category category, String name, double amount) {
        Entry entry = new Entry(category, name, amount);
        entries.add(entry);
        total += entry.getAmount();
        categoryMap.put(category, entry);
        notifyObservers(entries.size());
    }

    public String getUsedPercentage() {
        DecimalFormat d = new DecimalFormat();
        d.setMaximumFractionDigits(2);
        String percent = d.format((total/limit*100)) + "%";
        return percent;
    }

    public Map<Category, Entry> getCategoryMap() {
        return categoryMap;
    }
}
