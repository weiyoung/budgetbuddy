package model.categories;

import model.Category;

public class Utilities implements Category {

    String name;
    String color;

    public Utilities() {
        name = "Utilities";
        color = "purple";
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
