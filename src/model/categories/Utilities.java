package model.categories;

import model.Category;

public class Utilities extends Category {

    private Rent rentWithUtilities = new Rent();

    public void setRent(Rent r) {
        if (getRent().equals(rentWithUtilities)) {
            rentWithUtilities = r;
            r.setUtilities(this);
        }

    }

    public Rent getRent() {
        return rentWithUtilities;
    }

    public Utilities() {
        setName("Utilities");
        setColor("purple");
    }

}
