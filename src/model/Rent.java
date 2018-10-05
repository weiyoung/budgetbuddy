package model;

public class Rent implements Category {

    String color = "";

    @Override
    public void getColor() {
        this.color = "pink";
    }

}
