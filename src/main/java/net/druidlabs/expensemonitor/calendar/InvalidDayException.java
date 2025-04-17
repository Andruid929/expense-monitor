package net.druidlabs.expensemonitor.calendar;

/**
 * Exception for when a day of month argument given is less than 1 and greater than 31.
 *
 * @author Andrew Jones
 * @since 1.0
 * @version 1.0
 * @see InvalidMonthException
 * */

public class InvalidDayException extends IllegalArgumentException {

    private final int dayOfMonth;

    /**
     * Constructor that takes in the invalid day given for use in the {@link #getMessage()}.
     *
     * @since 1.0
     * */

    public InvalidDayException(int dayOfMonth) {
        super();
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String getMessage() {
        return dayOfMonth + " is not a valid day of the given month";
    }
}
