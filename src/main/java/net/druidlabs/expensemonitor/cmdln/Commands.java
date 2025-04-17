package net.druidlabs.expensemonitor.cmdln;

/**
 * Class housing all registered commands in the expense monitor
 *
 * @since 1.0
 * @version 1.0
 * @author Andrew Jones
 * */

public class Commands {

    /**
     * Get all registered commands and extra info.
     *
     * @since 1.0
     * */

    public static final char HELP = 'H';

    /**
     * Add an expense to the log.
     *
     * @since 1.0
     * */

    public static final char ADD = 'A';

    /**
     * Get all the expenses logged in a specific month.
     *
     * @since 1.0
     * */

    public static final char MONTH_SUMMARY = 'M';

    /**
     * Remove an expense with the matching description.
     *
     * @since 1.0
     * */

    public static final char REMOVE = 'R';

    /**
     * Get all logged expenses regardless of the month.
     *
     * @since 1.0
     * */

    public static final char GET_ALL = 'G';

    /**
     * Clears all logged expenses, effectively starting from scratch.
     *
     * @since 1.0
     * */

    public static final char CLEAR = 'C';

    /**
     * Get the sum of all saved expenses.
     *
     * @since 1.0
     * */

    public static final char TOTAL = 'T';

    /**
     * Save changes and exit the expense monitor.
     *
     * @since 1.0
     * */

    public static final char EXIT = 'E';

    /**
     * Cancel expense creation when passing an amount.
     * */

    public static final int NUM_CANCEL = -1;

    //TODO add List for all commands
}
