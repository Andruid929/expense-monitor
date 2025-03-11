package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.Constants;
import net.druidlabs.expensemonitor.calendar.InvalidDayException;
import net.druidlabs.expensemonitor.calendar.TimeFunctions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;

public class Expense {

    private final LocalTime time = LocalTime.now();

    private final LocalDate date = LocalDate.now();

    private final int day = getDay();
    private final int year = getYear();

    private final String superscript = getSuperScript(day);
    private final String month = TimeFunctions.getMonth(Calendar.MONTH);

    private final int hour = getHour();
    private final int minute = getMinute();
    private final int amount;
    private final String description;


    public Expense(int amount, String description) {
        this.amount = amount;
        this.description = description;

        if (description.equals(Constants.TEST_DESC)) { //This description is used in testing and shouldn't be added to the expense list
            return;
        }

        Expenses.expenses.add(this);
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return month + " " + day + superscript + ", " + year; //Sample output: May 21st, 2004
    }

    public String getTime() {
        return formatTime(hour) + ":" + formatTime(minute);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public static String getSuperScript(int day) {
        if ((day < 1) || (day > 31)) {
            throw new InvalidDayException(day);
        }

        return switch (day) {
            case 1, 21, 31 -> "st";
            case 2, 22 -> "nd";
            case 3, 23 -> "rd";
            default -> "th";
        };
    }

    private int getHour() {
        return time.getHour();
    }

    private int getMinute() {
        return time.getMinute();
    }

    private int getYear() {
        return date.getYear();
    }

    private String formatTime(int unit) {
        return unit < 10 ? "0" + unit : String.valueOf(unit); //If the unit is a single digit like 9 or lower, place a zero before the unit
    }

    public int amount() {
        return amount;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Expense) obj;
        return this.amount == that.amount &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description);
    }

    @Override
    public String toString() {
        return "Expense[amount=" + amount + ", " +
                "description=" + description + ']';
    }

}
