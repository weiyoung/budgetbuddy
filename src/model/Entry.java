package model;

public class Entry {

    Category category;
    String entryName;
    double entryAmount;

    public Entry(Category category, String entryName, double entryAmount) {
        this.category = category;
        this.entryName = entryName;
        this.entryAmount = entryAmount;
    }

    public String getCategory() {
        return category.getName();
    }

    public String getEntryName() {
        return entryName;
    }

    public double getEntryAmount() {
        return entryAmount;
    }

}