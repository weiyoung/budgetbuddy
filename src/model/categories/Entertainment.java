package model.categories;

import model.CategoryWithLimit;
import model.Category;

public class Entertainment implements Category, CategoryWithLimit {

    String name;
    String color;
    double categoryLimit;

    public Entertainment() {
        name = "Entertainment";
        color = "orange";
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
