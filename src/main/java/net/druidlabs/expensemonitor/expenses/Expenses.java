package net.druidlabs.expensemonitor.expenses;

import net.druidlabs.expensemonitor.io.LoadExpenses;

import java.util.List;

public final class Expenses {

    static final List<Expense> expenses = LoadExpenses.loadExpenses();

    private Expenses() {}

    public static List<Expense> getExpenses() {
        return expenses;
    }

}
