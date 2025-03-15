package net.druidlabs.expensemonitor;

import java.io.File;

public class Constants {

    public static final String DEFAULT_SAVE_FOLDER_LOCATION = "C:\\Users\\" + System.getenv("USERNAME") + "\\Documents\\Expense monitor\\";

    public static final File SAVE_FOLDER = new File(DEFAULT_SAVE_FOLDER_LOCATION);

    public static final File SAVE_FILE = new File(SAVE_FOLDER, "Expenses.esp");

    public static final String TEST_DESC = "This is a test description";

}
