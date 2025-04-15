package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.expenses.Expense;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FILE;

/**
 * This class is what reads the save file to retrieve saved expenses.
 *
 * @author Andrew Jones
 * @version 1.0
 * @since 1.0
 * @see SaveExpenses
 * */

public class LoadExpenses {

    /**
     * Attempts to read the save file for saved expenses.
     * If the file does not exist or is corrupted, this will return an empty {@code List<Expense>}
     *
     * @return {@code List<Expense>} containing all saved expenses.
     * @since 1.0
     * */

    @SuppressWarnings("unchecked")
    public static List<Expense> loadSaveFile() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Expense>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

}