package test;

import model.BudgetBuddy;
import model.Category;
import model.Entry;
import model.categories.*;
import model.exceptions.Overspending.AlmostOverspendException;
import model.exceptions.Overspending.OverspendException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BudgetBuddyTest {

    BudgetBuddy tester;

    @BeforeEach
    public void setup() {
        tester = new BudgetBuddy();
        tester.setLimit(1000);
    }

    @Test
    public void testBudgetBuddy() {
        assertEquals(0, tester.getTotal());
        assertEquals(1000, tester.getLimit());
    }

    @Test
    public void testCreatingNewEntry() {
        Category testCategory = new Miscellaneous();
        Entry testEntry = new Entry(testCategory, "Skull Candy Headphones", 150);
        assertEquals("Miscellaneous", testEntry.getCategory());
        assertEquals("Skull Candy Headphones", testEntry.getEntryName());
        assertEquals(150, testEntry.getEntryAmount());
    }

    @Test
    public void testViewingSummary() {
        tester.createNewEntry(new Food(), "Running Chicken", 20);
        tester.viewSummary();
    }

    @Test
    public void testAlmostOverspendException() {
        Entry testEntry = new Entry(new Rent(), "$$$", 910);
        try {
            tester.checkBudget(testEntry);
            fail("Should throw AlmostOverspendException now...");
        } catch (AlmostOverspendException e) {
            //pass
        } catch (OverspendException e) {
            fail("Shouldn't throw this! Expecting AlmostOverspendException instead.");
        }
    }

    @Test
    public void testOverspendException() {
        Entry testEntry = new Entry(new Miscellaneous(), "Some super expensive shit", 1500);
        try {
            tester.checkBudget(testEntry);
            fail("Should throw AlmostOverspendException now...");
        } catch (AlmostOverspendException e) {
            fail("Wrong exception thrown!! shoudl throw OverspendException instead.");
        } catch (OverspendException e) {
            //pass
        }
    }




}
