package model.categories;

import model.Category;

public class Groceries implements Category {

    String name;
    String color;

    public Groceries() {
        name = "Groceries";
        color = "green";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

}
