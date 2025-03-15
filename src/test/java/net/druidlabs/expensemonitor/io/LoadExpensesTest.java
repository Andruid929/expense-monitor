package net.druidlabs.expensemonitor.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadExpensesTest {

    @Test
    void readSer() throws IOException, ClassNotFoundException {
        assertEquals(4, LoadExpenses.loadExpenses().size());
    }

}