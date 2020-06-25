package pl.wizyg.payroll.exception;

public class SickLeavesOverlapException extends Exception {
    public SickLeavesOverlapException(String message) {
        super(message);
    }
}
