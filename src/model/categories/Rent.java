package model.categories;

import model.Category;

public class Rent extends Category {

    private Utilities utilitiesOfRent = new Utilities();

    public void setUtilities(Utilities u) {
        if (getUtilities().equals(utilitiesOfRent)) {
            utilitiesOfRent = u;
            u.setRent(this);
        }
    }

    public Utilities getUtilities() {
        return utilitiesOfRent;
    }

    public Rent() {
        setName("Rent");
        setColor("pink");
    }

}
