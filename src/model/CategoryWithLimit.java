package model;

public interface CategoryWithLimit extends Category {

    double CATEGORY_LIMIT = 200;

    void getCategoryLimit();

}
