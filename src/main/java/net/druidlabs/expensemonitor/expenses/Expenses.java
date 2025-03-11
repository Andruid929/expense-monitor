package net.druidlabs.expensemonitor.expenses;

import java.util.ArrayList;
import java.util.List;

public final class Expenses {

    static final List<Expense> expenses = new ArrayList<>();

    private Expenses() {}

    public static List<Expense> getExpenses() {
        return expenses;
    }

}
