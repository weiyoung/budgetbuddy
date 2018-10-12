package test;

import model.BudgetBuddy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BudgetBuddyTest {

    BudgetBuddy tester;

    @BeforeEach
    public void setup() {
        tester = new BudgetBuddy();
    }

    @Test
    public void testBudgetBuddy() {
        assertEquals(0, tester.getTotal());
        assertEquals(0, tester.getLimit());
    }

//    @Test
//    public void testAddEntry() {
//        tester.createNewEntry(7, "Hoolala Chicken w/ friends", 18.79);
//        int expectedChoice = 7;
//        String expectedName = "Hoolala Chicken w/ friends";
//        double expectedAmount = 18.79;
//        assertEquals();
//    }
//
//    @Test
//    public void testCheckBudget() {
//
//    }


}
