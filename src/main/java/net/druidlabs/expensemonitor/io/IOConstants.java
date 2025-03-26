package net.druidlabs.expensemonitor.io;

import java.io.File;

public final class IOConstants {

    private static final String DEFAULT_SAVE_FOLDER = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Expenses manager" + File.separator;

    public static final File SAVE_FOLDER = new File(DEFAULT_SAVE_FOLDER);

    public static final File SAVE_FILE = new File(SAVE_FOLDER, "Expenses.esp");

}
