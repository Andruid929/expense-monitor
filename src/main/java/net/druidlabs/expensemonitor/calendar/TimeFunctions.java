package net.druidlabs.expensemonitor.calendar;

import java.util.List;

public final class TimeFunctions {

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

    private static final List<String> MONTHS_OF_YEAR_SHORT = MONTHS_OF_YEAR.stream().map(s -> s.substring(0, 3)).toList();

    private TimeFunctions() {
    }

    public static String getMonth(int month) {
        if (month < 1 || month > 12) {
            throw new InvalidMonthException(month);
        }

        return MONTHS_OF_YEAR.get(month);
    }

    public static List<String> getMonthsOfYearShort() {
        return MONTHS_OF_YEAR_SHORT;
    }


}
