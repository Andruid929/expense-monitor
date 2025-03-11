package net.druidlabs.expensemonitor.io;

import net.druidlabs.expensemonitor.Constants;
import net.druidlabs.expensemonitor.expenses.Expense;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class SaveExpenses {

    private static final File saveFolder = new File(Constants.DEFAULT_SAVE_FOLDER);

    private static int number;

    public static void createSave(List<Expense> expenses) throws IOException {

        if (!saveFolder.exists()) {
            boolean _ = saveFolder.mkdir();
        }

        if (expenses.isEmpty()) {
            return;
        }

        for (Expense expense : expenses) {
            number++;

            String desc = expense.description();
            int amount = expense.amount();
            String month = expense.getMonth();
            String date = expense.getDate();

            File file = new File(Constants.DEFAULT_SAVE_FOLDER + expense.description().substring(0, 3) + number + ".exp");

            boolean _ = file.createNewFile();

            try (FileWriter fw = new FileWriter(file);
                 BufferedWriter writer = new BufferedWriter(fw)) {

                writer.write(desc + "\r\n");
                writer.write(amount + "\r\n");
                writer.write(month + "\r\n");
                writer.write(formatDate(date, month));
            }
        }
    }

    static String formatDate(String fullDate, String month) {
        return fullDate.substring(month.length() + 1);
    }

}
