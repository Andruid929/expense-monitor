package net.druidlabs;

import net.druidlabs.expensemonitor.cmdln.CommandListener;

import java.util.Scanner;

/**
 * Every Java program's favourite class.
 *
 * @author Andrew Jones
 * @version 1.0
 * @since 1.0
 * */

public class Main {

    /**
     * The scanner that is in charge of all user input.
     *
     * @since 1.0
     * */

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The infamous {@code main} method
     *
     * @param args a string array.
     * @since 1.0
     * */

    public static void main(String[] args) {
        runExpenseMonitor();
    }

    /**
     * Prints opening message to the terminal and then launches the {@link CommandListener}
     *
     * @since 1.0
     * */

    private static void runExpenseMonitor() { //Call in main method to start
        System.out.println("Expense tracker");
        System.out.println("Enter a command to get started or enter 'HELP' or 'H' for a list of commands");

        CommandListener.launch(scanner);
    }

}
