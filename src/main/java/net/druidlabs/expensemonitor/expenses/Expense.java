package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.Constants;
import net.druidlabs.expensemonitor.calendar.InvalidDayException;
import net.druidlabs.expensemonitor.calendar.TimeFunctions;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;

public class Expense implements Serializable {

    private final LocalTime time;

    private final LocalDate date;

    private final int day;
    private final int year;

    private final String superscript;
    private final String month;
    private final String description;

    private final int hour;
    private final int minute;
    private final int amount;

    public Expense(int amount, String description) {
        this.amount = amount;
        this.description = description;

        time = LocalTime.now();
        date = LocalDate.now();

        day = date.getDayOfMonth();
        year = date.getYear();

        superscript = getSuperScript(day);

        month = TimeFunctions.getMonth(Calendar.MONTH);

        hour = time.getHour();

        minute = time.getMinute();

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

    public String getSuperScript(int day) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return day == expense.day && year == expense.year && hour == expense.hour && minute == expense.minute && amount == expense.amount && Objects.equals(time, expense.time) && Objects.equals(date, expense.date) && Objects.equals(superscript, expense.superscript) && Objects.equals(month, expense.month) && Objects.equals(description, expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, date, day, year, superscript, month, hour, minute, amount, description);
    }

    @Override
    public String toString() {
        return "Expense[amount=" + amount + ", " +
                "description=" + description + ']';
    }

}
