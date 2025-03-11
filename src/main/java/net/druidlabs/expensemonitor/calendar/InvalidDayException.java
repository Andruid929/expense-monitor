package net.druidlabs.expensemonitor.calendar;

public class InvalidDayException extends IllegalArgumentException {

    private int dayOfMonth;

    public InvalidDayException(int dayOfMonth) {
        super();
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String getMessage() {
        return dayOfMonth + " is not a valid day of the given month";
    }
}
