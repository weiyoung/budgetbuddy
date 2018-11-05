package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(category, entry.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

}
