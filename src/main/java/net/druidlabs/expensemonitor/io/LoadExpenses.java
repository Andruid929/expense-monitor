package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.expenses.Expense;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FILE;

public class LoadExpenses {

    public static List<Expense> loadExpenses() {

        try (FileInputStream fis = new FileInputStream(SAVE_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Expense>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

}