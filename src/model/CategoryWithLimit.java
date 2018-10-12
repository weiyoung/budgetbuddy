package model;

public abstract class CategoryWithLimit extends Category {

    double categoryLimit;

    public double getCategoryLimit() {
        return categoryLimit;
    }

    public void setCategoryLimit(double categoryLimit) {
        this.categoryLimit = categoryLimit;
    }
}
