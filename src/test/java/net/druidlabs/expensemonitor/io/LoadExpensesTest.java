package net.druidlabs.expensemonitor.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadExpensesTest {

    @Test
    void readSer() {
        assertEquals(4, LoadExpenses.loadExpenses().size());
    }

}