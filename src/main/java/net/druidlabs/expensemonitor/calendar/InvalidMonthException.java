package net.druidlabs.expensemonitor.calendar;

public class InvalidMonthException extends IllegalArgumentException {

    private final int invalidMonthNumber;

    public InvalidMonthException(int invalidMonthNumber) {
        super();
        this.invalidMonthNumber = invalidMonthNumber;
    }

    @Override
    public String getMessage() {
        return "[" + invalidMonthNumber + "] is not a valid month number, enter a number between 1 and 12.";
    }
}
