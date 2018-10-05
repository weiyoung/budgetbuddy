package model;

public class Bills implements Category {

    String color = "";

    @Override
    public void getColor() {
        this.color = "blue";
    }

}
