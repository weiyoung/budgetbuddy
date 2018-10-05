package model;

public class Entertainment implements Category, CategoryWithLimit {

    String color = "";
    double categoryLimit = 0;

    @Override
    public void getColor() {
        this.color = "orange";
    }

    @Override
    public void getCategoryLimit() {
        this.categoryLimit = 0.5 * CATEGORY_LIMIT;
    }

}
