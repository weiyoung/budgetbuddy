package ui;

import model.BudgetBuddy;
import model.Category;
import model.Entry;
import model.exceptions.InvalidOptionException;

public class SummaryChecker {

    private BudgetBuddy buddy;
    private Menu menu;
    private static SummaryChecker thisSummaryChecker = null;

    private SummaryChecker(BudgetBuddy buddy, Menu menu) {
        this.buddy = buddy;
        this.menu = menu;
    }

    public static SummaryChecker getInstance(BudgetBuddy buddy, Menu menu) {
        if (thisSummaryChecker == null)
            thisSummaryChecker = new SummaryChecker(buddy, menu);
        return thisSummaryChecker;
    }

    protected void monthlySummary() {
        menu.checkBudget();
        for (Entry e: buddy.getEntries()) {
            System.out.printf("Category: %s \n", e.getCategory());
            System.out.printf("Tag: %s \n", e.getName());
            System.out.printf("Amount: $%.2f \n\n", e.getAmount());
        }
    }

    protected void summaryByCategory() {
        Category category = null;
        try {
            int option = menu.choosingCategory();
            category = menu.categorySwitch(option);
        } catch (InvalidOptionException e) {
            System.out.println("Invalid option.");
        }
        viewSummaryByCategory(category);
    }

    protected void viewSummaryByCategory(Category category) {
        int count = 0;
        for (Entry e : buddy.getEntries()) {
            if (e.equals(buddy.getCategoryMap().get(category))) {
                System.out.println("(" + e.getCategory() + ")");
                System.out.printf("Tag: %s \n", e.getName());
                System.out.printf("Amount: $%.2f \n\n", e.getAmount());
            } else
                count++;
        }
        if (buddy.getEntries() == null || count == buddy.getEntries().size())
            System.out.println("No entries recorded.");
    }

}
