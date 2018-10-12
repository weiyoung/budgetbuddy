package model.categories;

import model.Category;

public class Rent implements Category {

    String name;
    String color;

    public Rent() {
        name = "Rent";
        color = "pink";
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
