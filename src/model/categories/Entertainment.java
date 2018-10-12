package model.categories;

import model.CategoryWithLimit;

public class Entertainment extends CategoryWithLimit {

    public String entertainmentType;

    public Entertainment() {
        setName("Entertainment");
        setColor("orange");
        setCategoryLimit(100);
        setEntertainmentType("Movie");
    }

    public String getEntertainmentType() {
        return entertainmentType;
    }

    public void setEntertainmentType(String entertainmentType) {
        this.entertainmentType = entertainmentType;
    }
}
