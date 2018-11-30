package model;

import model.categories.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Saves {

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void save(BudgetBuddy b) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("BudgetBuddySave.txt","UTF-8");
        writer.printf("%.2f\n", b.getTotal());
        writer.printf("%.2f\n", b.getLimit());
        for (Entry e: b.getEntries())
            writer.printf("%s|%s|%.2f\n", e.getCategory(), e.getName(), e.getAmount());
        writer.close();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void load(BudgetBuddy b) throws IOException {
        List<String> lines  = Files.readAllLines(Paths.get("BudgetBuddySave.txt"));
        if (isSaveFileEmpty(lines))
            throw new IOException();
        b.setTotal(0);
        b.setLimit(Double.parseDouble(lines.get(1)));
        for (int i = 2; i < lines.size(); i++) {
            String[] dataArray = lines.get(i).split("\\|");
            System.out.println(Arrays.toString(dataArray));
            Category c;
            if (dataArray[0].equals("Food")) c = new Food();
            else if (dataArray[0].equals("Groceries")) c = new Groceries();
            else if (dataArray[0].equals("Entertainment")) c = new Entertainment();
            else if (dataArray[0].equals("Bills")) c = new Bills();
            else if (dataArray[0].equals("Utilities")) c = new Utilities();
            else if (dataArray[0].equals("Rent")) c = new Rent();
            else c = new Miscellaneous();
            double d = Double.parseDouble(dataArray[2]);
            b.createEntry(c, dataArray[1], d);
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public boolean isSaveFileEmpty(List<String> lines) {
        boolean emptiness = false;
        for (String s: lines )
            if (s.isEmpty())
                emptiness = true;
        return emptiness;
    }

}
