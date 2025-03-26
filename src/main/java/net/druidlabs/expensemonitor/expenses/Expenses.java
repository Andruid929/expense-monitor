package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.io.LoadExpenses;

import java.util.List;

public final class Expenses {

    static final List<Expense> expenses = LoadExpenses.loadSaveFile();

    private Expenses() {}

    public static List<Expense> getExpenses() {
        return expenses;
    }

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
