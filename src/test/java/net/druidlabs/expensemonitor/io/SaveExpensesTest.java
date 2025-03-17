package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.expenses.Expense;
import net.druidlabs.expensemonitor.values.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveExpensesTest {

    private final Expense expense = new Expense(455, Strings.TEST_DESC);

    @Test
    void formatDate() {
        assertEquals("11th, 2025", SaveExpenses.formatDate(expense.getDate(), expense.getMonth()));
    }
}