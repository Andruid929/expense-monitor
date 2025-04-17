package net.druidlabs.expensemonitor.calendar;

import java.util.List;

/**
 * Utility class for operations that require the month the operation was performed.
 *
 * @author Andrew Jones
 * @version 1.0
 * @since 1.0
 */

public final class MonthFunctions {

    /**
     * Unmodifiable list of the 12 known months of the year in full.
     *
     * @since 1.0
     */

    private static final List<String> MONTHS_OF_YEAR = List.of(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    );

    /**
     * Unmodifiable list of the known 12 months of the year in short;
     *
     * @since 1.0
     */

    private static final List<String> MONTHS_OF_YEAR_SHORT = MONTHS_OF_YEAR.stream().map(s -> s.substring(0, 3)).toList();

    private MonthFunctions() {
    }

    /**
     * Zero-based month that returns the month linked to the month number given.
     *
     * <p>{@code January} if the argument is {@code 0}, {@code February} for {@code 1} and so on.
     *
     * @return {@code String} month marked by the given number.
     * @param monthNumber number marking the month to be had.
     * @throws InvalidMonthException if the month number given is bigger than 12 or smaller than 1.
     * @since 1.0
     */

    public static String getMonth(int monthNumber) throws InvalidMonthException {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new InvalidMonthException(monthNumber);
        }

        return MONTHS_OF_YEAR.get(monthNumber);
    }

    /**
     * Get the first 3 letters of the month for all 12 months.
     *
     * @return {@code List<String>} containing all shortened month names.
     * @since 1.0
     * */

    public static List<String> getMonthsOfYearShort() {
        return MONTHS_OF_YEAR_SHORT;
    }


}
