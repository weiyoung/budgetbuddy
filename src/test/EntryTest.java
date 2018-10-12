package test;

import model.Entry;
import model.Category;
import model.categories.Groceries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {

    Entry tester;
    Category testCategory = new Groceries();
    String testEntryName = "Mc Donald's";
    double testEntryAmount = 5.95;

    @BeforeEach
    public void setup() {
        tester = new Entry(testCategory, testEntryName, testEntryAmount);
    }

    @Test
    public void setTestCategory() {
        assertEquals(tester.getCategory(), testCategory.getName());
    }

    @Test
    public void setTestEntryName() {
        assertEquals(tester.getEntryName(), testEntryName);
    }

    @Test
    public void setTestEntryAmount() {
        assertEquals(tester.getEntryAmount(), testEntryAmount);
    }


}
