package model.exceptions;

public class NegativeBudgetException extends Exception {

    public NegativeBudgetException() {}

    public NegativeBudgetException(String s) {
        super(s);
    }

}
