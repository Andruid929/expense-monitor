package net.druidlabs.expenses;

import net.druidlabs.expensemonitor.calendar.TimeFunctions;
import net.druidlabs.expensemonitor.expenses.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    private final Expense expense = new Expense(100, "Test expense");

    @Test
    void getMonth() {
        assertEquals("March", expense.getMonth());
    }

    @Test
    void getShortMonth() {
        assertEquals("Sep", TimeFunctions.getMonthsOfYearShort().get(8));
    }

}