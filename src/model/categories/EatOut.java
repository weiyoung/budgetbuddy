package model.categories;

import model.CategoryWithLimit;
import model.Category;

public class EatOut implements Category, CategoryWithLimit {

    String name;
    String color;
    double categoryLimit;

    public EatOut() {
        name = "Eat Out";
        color = "yellow";
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
        categoryLimit = CATEGORY_LIMIT;
    }

}
