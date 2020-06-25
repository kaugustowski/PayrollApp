package pl.wizyg.payroll.exception;

public class SickLeaveNotFoundException extends Exception {
    public SickLeaveNotFoundException(String message) {
        super(message);
    }

    public SickLeaveNotFoundException() {

    }
}
