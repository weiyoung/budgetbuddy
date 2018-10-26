package test;

import model.BudgetBuddy;
import model.Category;
import model.Entry;
import model.categories.*;
import model.exceptions.CloseToOverspendingException;
import model.exceptions.ExceededTotalException;
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
        tester.createNewEntry(1, "Running Chicken", 20);
        tester.viewSummary();
    }

    @Test
    public void testCloseToOverspendingException() {
        Entry testEntry = new Entry(new Rent(), "$$$", 950);
        try {
            tester.checkBudget(testEntry);
            fail("Should throw CloseToOverspendingException now...");
        } catch (CloseToOverspendingException e) {
            //pass
        } catch (ExceededTotalException e) {
            fail("Shouldn't throw this! Expecting CloseToOverspendingException instead.");
        }
    }

    @Test
    public void testExceededTotalException() {
        Entry testEntry = new Entry(new Miscellaneous(), "Some super expensive shit", 1500);
        try {
            tester.checkBudget(testEntry);
            fail("Should throw CloseToOverspendingException now...");
        } catch (CloseToOverspendingException e) {
            fail("Wrong exception thrown!! shoudl throw ExceededTotalException instead.");
        } catch (ExceededTotalException e) {
            //pass
        }
    }




}
