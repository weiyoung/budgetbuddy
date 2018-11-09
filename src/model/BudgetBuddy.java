package model;

import model.exceptions.Overspending.AlmostOverspendException;
import model.exceptions.Overspending.OverspendException;
import model.exceptions.InvalidChoiceException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static model.Helper.isSaveFileEmpty;
import static model.Helper.printf;
import static model.Helper.println;

public class BudgetBuddy {

    private double total;
    private double limit;
    private ArrayList<Entry> entries;
    private Map<Category, Entry> categoryMap = new HashMap<>();

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

    public void createNewEntry(Category category, String name, double entryAmount) throws InvalidChoiceException {
        Entry entry = new Entry(category, name, entryAmount);
        entries.add(entry);
        categoryMap.put(category, entry);
        try {
            checkBudget(entry);
        } catch (OverspendException e) {
            printf("LIMIT EXCEEDED BY $%.2f!!! \nPlease be aware of how much you spent!!\n\n", getTotal() - getLimit());
        } catch (AlmostOverspendException e) {
            printf("You have spent $%.2f so far. \nThis is more than 90%% of your budget. \nPlease beware of your spendings.\n\n", getTotal());
        }
    }

    public void checkBudget(Entry entry) throws OverspendException, AlmostOverspendException {
        total += entry.entryAmount;
        if (getTotal() > getLimit())
            throw new OverspendException();
        else if (getTotal()/getLimit() > 0.9)
            throw new AlmostOverspendException();
        else
            printf("You have spent $%.2f so far. \n", getTotal());
    }

    public void viewSummary() {
        for (Entry e: entries) {
            printf("Category: %s \n", e.getCategory());
            printf("Tag: %s \n", e.getEntryName());
            printf("Amount: $%.2f \n\n", e.getEntryAmount());
        }
    }

    public void checkSummaryByCategory(Category category) throws InvalidChoiceException {
        for (Entry e: entries) {
            if (e.equals(categoryMap.get(category))) {
                printf("Tag: %s \n", e.getEntryName());
                printf("Amount: $%.2f \n\n", e.getEntryAmount());
            }
        }
    }

    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("BudgetBuddySave.txt","UTF-8");
        writer.println(this.total);
        writer.println(this.limit);
        for (Entry e: entries) {
            writer.printf("%s;%s;$%.2f\n", e.getCategory(), e.getEntryName(), e.getEntryAmount());
        }
        writer.close();
    }

    public void load() throws IOException {
        List<String> lines  = Files.readAllLines(Paths.get("BudgetBuddySave.txt"));
        if (isSaveFileEmpty(lines))
            throw new IOException();
        this.total = Double.parseDouble(lines.get(0));
        this.limit = Double.parseDouble(lines.get(1));
        for (int i = 2; i < lines.size(); i++)
            lines.get(i).split(";");
        println("Memory loaded.");
    }

}
