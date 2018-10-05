package model;

public class Miscellaneous implements Category, CategoryWithLimit {

    String color = "";
    double categoryLimit = 0;

    @Override
    public void getColor() {
        this.color = "red";
    }

    @Override
    public void getCategoryLimit() {
        categoryLimit = 0.5 * CATEGORY_LIMIT;
    }

}
