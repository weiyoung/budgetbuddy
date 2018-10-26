package model.exceptions;

public class ExceededTotalException extends OverspendingException {

    public ExceededTotalException() {}

    public ExceededTotalException(String s) {
        super(s);
    }

}
