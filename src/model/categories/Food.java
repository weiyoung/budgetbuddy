package model.categories;

import model.CategoryWithLimit;

public class Food extends CategoryWithLimit {

    public Food() {
        setName("Food");
        setColor("yellow");
        setCategoryLimit(200);
    }

    @Override
    public double getCategoryLimit() {
        return super.getCategoryLimit();
    }

}
