package model;

public class EatOut implements Category, CategoryWithLimit {

    String color = "";
    double categoryLimit = 0;

    @Override
    public void getColor() {
        this.color = "yellow";
    }

    @Override
    public void getCategoryLimit() {
        categoryLimit = CATEGORY_LIMIT;
    }

}
