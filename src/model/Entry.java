package model;

import java.util.Objects;

public class Entry {

    private Category category;
    private String name;
    private double amount;

    public Entry(Category category, String name, double amount) {
        this.category = category;
        this.name = name;
        this.amount = amount;
    }

    public String  getCategory() {
        return category.getName();
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Double.compare(entry.amount, amount) == 0 &&
                Objects.equals(category, entry.category) &&
                Objects.equals(name, entry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, amount);
    }

}
