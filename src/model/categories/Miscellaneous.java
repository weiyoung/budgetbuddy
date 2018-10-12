package model.categories;

import model.CategoryWithLimit;
import model.Category;

public class Miscellaneous implements Category, CategoryWithLimit {

    String name;
    String color;
    double categoryLimit;

    public Miscellaneous() {
        name = "Miscellaneous";
        color = "red";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void getCategoryLimit() {
        categoryLimit = 0.5 * CATEGORY_LIMIT;
    }

}
