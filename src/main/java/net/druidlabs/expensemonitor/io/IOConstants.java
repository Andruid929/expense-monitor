package net.druidlabs.expensemonitor.io;

import java.io.File;

public final class IOConstants {

    public static final String DEFAULT_SAVE_FOLDER_LOCATION = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Expenses manager" + File.separator;

    public static final File SAVE_FOLDER = new File(DEFAULT_SAVE_FOLDER_LOCATION);

    public static final File SAVE_FILE = new File(SAVE_FOLDER, "Expenses.esp");

}
