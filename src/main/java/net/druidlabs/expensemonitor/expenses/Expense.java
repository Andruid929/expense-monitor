package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.calendar.InvalidDayException;
import net.druidlabs.expensemonitor.calendar.MonthFunctions;
import net.druidlabs.expensemonitor.values.Strings;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * This class tracks down expenses including the amount, description of the expense, date and time of the expense.
 *
 * @since 1.0
 * @version 1.0
 * @author Andrew Jones
 * @see Expenses
 * */

public class Expense implements Serializable {

    /**
     * Gets the exact {@code time} an expense was logged.
     *
     * @since 1.0
     * */

    private final LocalTime time;

    /**
     * Gets the exact {@code date} an expense was logged.
     *
     * @since 1.0
     * */

    private final LocalDate date;

    /**
     * Day of the month the expense was logged
     *
     * @since 1.0
     * */

    private final int day;

    /**
     * Year the expense was logged.
     *
     * @since 1.0
     * */
    private final int year;

    /**
     * The superscript following the date.
     * For example, {@code st}, {@code nd}, {@code rd} and {@code th}.
     *
     * @since 1.0
     * */

    private final String superscript;

    /**
     * The month the expense was logged.
     *
     * @since 1.0
     * */

    private final String month;

    /**
     * The description of the expense.
     *
     * @since 1.0
     * */

    private final String description;

    /**
     * The exact hour the expense was logged.
     *
     * @since 1.0
     * */

    private final int hour;

    /**
     * The exact minute the expense was logged.
     *
     * @since 1.0
     * */

    private final int minute;

    /**
     * The amount that was logged.
     *
     * @since 1.0
     * */

    private final int amount;

    /**
     * Creating an object will automatically log the expense to the expense list unless the description is {@code Test desc}.
     *
     * @since 1.0
     * @see Strings
     * @param amount the amount spent.
     * @param description description of the amount spent.
     * */

    public Expense(int amount, String description) {
        this.amount = amount;
        this.description = description;

        time = LocalTime.now();
        date = LocalDate.now();

        day = date.getDayOfMonth();
        year = date.getYear();

        superscript = getSuperScript(day);

        month = MonthFunctions.getMonth(date.getMonthValue() - 1);

        hour = time.getHour();

        minute = time.getMinute();

        if (description.equals(Strings.TEST_DESC)) { //This description is used in testing and shouldn't be added to the expense list
            return;
        }

        Expenses.getExpenses().add(this);
    }

    /**
     * @return {@code String} the month that the expense happened.
     * @since 1.0
     * */

    public String getMonth() {
        return month;
    }

    /**
     * @return {@code String} the date that the expense happened.
     * @since 1.0
     * */

    public String getDate() {
        return month + " " + day + superscript + ", " + year; //Sample output: May 21st, 2004
    }

    /**
     * @return {@code String} the hour and time that the expense happened.
     * @since 1.0
     * */

    public String getTime() {
        return formatTime(hour) + ":" + formatTime(minute);
    }

    /**
     * @return {@code String} the superscript for the day.
     * @since 1.0
     * */

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

    /**
     * Helper method that formats date and time units.
     *
     * @return {@code String} formatted unit, for example {@code 05} if unit is {@code 5}.
     * @since 1.0
     * */

    private String formatTime(int unit) {
        return unit < 10 ? "0" + unit : String.valueOf(unit); //If the unit is a single digit like 9 or lower, place a zero before the unit
    }

    /**
     * @return {@code int} the amount spent.
     * @since 1.0
     * */

    public int amount() {
        return amount;
    }

    /**
     * @return {@code String} the description of the expense.
     * @since 1.0
     * */

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
