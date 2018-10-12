package model.categories;

import model.Category;

public class Bills implements Category {

    String name;
    String color;

    public Bills() {
        name = "Bills";
        color = "blue";
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
