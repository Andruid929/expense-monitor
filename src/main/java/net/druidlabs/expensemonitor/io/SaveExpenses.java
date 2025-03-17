package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.expenses.Expenses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FILE;
import static net.druidlabs.expensemonitor.io.IOConstants.SAVE_FOLDER;

public final class SaveExpenses {

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

    static String formatDate(String fullDate, String month) {
        return fullDate.substring(month.length() + 1);
    }

}
