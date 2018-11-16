package observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<BudgetObserver> observers = new ArrayList<>();

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void addObserver(BudgetObserver budgetObserver) {
        if (!observers.contains(budgetObserver))
            observers.add(budgetObserver);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void notifyObservers(int amount) {
        for (BudgetObserver observer: observers)
            observer.update(amount);
    }

}
