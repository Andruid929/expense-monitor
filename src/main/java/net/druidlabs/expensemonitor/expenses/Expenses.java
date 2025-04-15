package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.io.LoadExpenses;

import java.util.List;

/**
 * This class is responsible for holding the {@code List} that contains all the logged expenses.
 *
 * @author Andrew Jones
 * @since 1.0
 * @version 1.0
 * @see Expense
 * */

public final class Expenses {

    /**
     * This is the {@code List} itself that saves and loads the expenses loaded.
     *
     * @since 1.0
     * */

    private static final List<Expense> expenses = LoadExpenses.loadSaveFile();

    private Expenses() {}

    /**
     * @return {@code List<Expense>} all saved expense in insertion order.
     * @since 1.0
     * */

    public static List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * @return {@code int} total sum of all saved expenses.
     * @since 1.0
     * */

    public static int getTotalAmountSpent() {
        int amountSpent = 0;

        if (expenses.isEmpty()) {
            return amountSpent;
        }

        for (Expense expense : expenses) {
            amountSpent += expense.amount();
        }

        return amountSpent;
    }

}
