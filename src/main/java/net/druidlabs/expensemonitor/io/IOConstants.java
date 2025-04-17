package net.druidlabs.expensemonitor.io;

import java.io.File;
import net.druidlabs.expensemonitor.values.Strings;
import net.druidlabs.expensemonitor.cmdln.Commands;

/**
 * This class keeps all files commonly used in the program's input and output operations.
 *
 * @since 1.0
 * @version 1.0
 * @author Andrew Jones
 * @see Strings
 * @see Commands
 * */

public final class IOConstants {

    /**
     * The default location of the {@code Expense monitor} save folder.
     *
     * @since 1.0
     * */

    private static final String DEFAULT_SAVE_FOLDER = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Expenses manager" + File.separator;

    /**
     * A {@link File} pointing to the default save folder.
     *
     * @since 1.0
     * */

    public static final File SAVE_FOLDER = new File(DEFAULT_SAVE_FOLDER);

    /**
     * A {@link File} pointing to the default save file.
     *
     * @since 1.0
     * */

    public static final File SAVE_FILE = new File(SAVE_FOLDER, "Expenses.esp");

}
