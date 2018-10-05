package model;

public class Groceries implements Category {

    String color = "";

    @Override
    public void getColor() {
        this.color = "green";
    }

}
