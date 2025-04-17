package net.druidlabs.expensemonitor.calendar;

/**
 * Exception for when a month of year argument is greater than 12 or less than 1.
 *
 * @author Andrew Jones
 * @since 1.0
 * @version 1.0
 * @see InvalidDayException
 * */

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
