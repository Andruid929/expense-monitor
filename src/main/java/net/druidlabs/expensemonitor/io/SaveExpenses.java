package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.expenses.Expenses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FILE;
import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FOLDER;

/**
 * This class is responsible for saving the list of expenses.
 *
 * @author Andrew Jones
 * @since 1.0
 * @version 1.0
 * @see LoadExpenses
 * */

public final class SaveExpenses {

    /**
     * Attempts to save the list of expenses by overwriting the save file.
     *
     * @since 1.0
     * @throws IOException if any output error occurs.
     * */

    public static void createSave() throws IOException {
        if (!SAVE_FOLDER.exists()) {
            boolean _ = SAVE_FOLDER.mkdir();
            boolean _ = SAVE_FILE.createNewFile();
        }

        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Expenses.getExpenses());
        }
    }

}
