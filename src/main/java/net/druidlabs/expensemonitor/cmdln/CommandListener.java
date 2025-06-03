package net.druidlabs.expensemonitor.cmdln;

import net.druidlabs.expensemonitor.calendar.MonthFunctions;
import net.druidlabs.expensemonitor.expenses.Expense;
import net.druidlabs.expensemonitor.expenses.Expenses;
import net.druidlabs.expensemonitor.expenses.Manager;
import net.druidlabs.expensemonitor.io.SaveExpenses;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;

import static net.druidlabs.expensemonitor.cmdln.Commands.*;

/**
 * Class responsible for handling commands and running tasks.
 * Uses a {@code scanner} take user input.
 *
 * @author Andrew Jones
 * @version 1.0
 * @see Commands
 * @see Manager
 * @since 1.0
 */

public abstract class CommandListener {

    /**
     * Launch the command listener
     *
     * @param scanner scanner object to be used to take user input.
     * @since 1.0
     */

    public static void launch(Scanner scanner) {
        String command = scanner.next();

        switch (command.toUpperCase()) {
            case "H", "HELP" -> printCommandList(scanner);
            case "A", "ADD" -> addExpense(scanner);
            case "E", "EXIT" -> exitManager();
            case "M", "MON SUM" -> monthSummary(scanner);
            case "R", "REMOVE" -> removeExpense(scanner);
            case "T", "TOTAL" -> totalSpent(scanner);
            case "G", "GET ALL" -> allLoggedExpenses(scanner);
            case "C", "CLEAR" -> cleanExpenses(scanner);
            default -> unknownCommand(command, scanner);
        }
    }

    /**
     * Clear out all saved expenses by calling List#clear on the list that holds all saved expenses.
     *
     * @param scanner scanner object to take user input.
     * @since 1.0
     */

    private static void cleanExpenses(Scanner scanner) {
        System.out.println("\nAre you sure? Logged expenses will be gone for a long time (Forever)");

        String response = scanner.next();

        if (response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y")) {
            Manager.clearHistory();
        } else {
            System.out.println("\nCancelled expense clearing");
        }

        System.out.println();
        askForCommand();
        launch(scanner);
    }

    /**
     * Print all saved expenses to the terminal
     *
     * @param scanner scanner object to take user input.
     * @since 1.0
     */

    private static void allLoggedExpenses(Scanner scanner) {
        System.out.println();
        Manager.getAllExpenses();

        askForCommand();
        launch(scanner);
    }

    /**
     * Print the sum of all saved expenses.
     *
     * @param scanner scanner object to take user input.
     * @since 1.0
     */

    private static void totalSpent(Scanner scanner) {
        System.out.println("\nTotal spent: " + Expenses.getTotalAmountSpent() + "\n");

        askForCommand();
        launch(scanner);
    }

    /**
     * Save the changes made while the expense monitor was running, the only way to save the expenses as of {@code v1.0}.
     *
     * @since 1.0
     */

    @SuppressWarnings("BusyWait")
    private static void exitManager() {
        System.out.print("\nExiting expense manager");

        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<String> process = service.submit(() -> {
                Thread.sleep(2500);
                return "______________________";
            });

            SaveExpenses.createSave();

            while (!process.isDone()) {
                System.out.print(".");
                Thread.sleep(1000);
            }

            System.out.println("\n\n" + process.get(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException | IOException e) {
            System.out.println("\n");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Remove a specified expense from the logs.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * */

    private static void removeExpense(Scanner scanner) {
        System.out.println("\nEnter description of the expense you want to remove");

        scanner.nextLine();

        String description = scanner.nextLine();

        checkIfBlank(description, "I cannot remove an expense unless I know the exact description", scanner);

        try {
            Manager.deleteExpense(description);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace(System.err);
            askForCommand();
            launch(scanner);
        }

        askForCommand();
        launch(scanner);
    }

    /**
     * Print all saved expenses logged within a specific month.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * */

    private static void monthSummary(Scanner scanner) {
        System.out.println("\nWhich month's expenses do you want to get?");

        String month = scanner.next();

        checkIfBlank(month, "Month is required", scanner);

        if (month.length() < 3) {
            System.out.println("A month name should start with at least 3 letters\n");
            askForCommand();
            launch(scanner);
        }

        boolean isExistingMonth = MonthFunctions.getMonthsOfYearShort().stream().anyMatch(s -> month.substring(0, 3).toUpperCase().equalsIgnoreCase(s));

        if (isExistingMonth) {
            System.out.println();
            Manager.getMonthExpenses(month);
        } else {
            System.out.println("\nInvalid month!");
            System.out.println();
        }

        askForCommand();
        launch(scanner);
    }

    /**
     * Print out all registered commands to the terminal.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * */

    private static void printCommandList(Scanner scanner) {
        System.out.print("\n'" + HELP + "' or 'HELP' for a list of commands" + "\n");
        System.out.print("'" + ADD + "' or 'ADD' to log down a new expense" + "\n");
        System.out.print("'" + MONTH_SUMMARY + "' or 'MON SUM' to get expenses logged within a specific month" + "\n");
        System.out.print("'" + REMOVE + " or 'REMOVE' to remove a specific expense" + "\n");
        System.out.print("'" + TOTAL + " or 'TOTAL' to get the total amount of money spent" + "\n");
        System.out.print("'" + GET_ALL + "' or 'GET ALL' to get all saved expenses" + "\n");
        System.out.print("'" + CLEAR + "' or 'CLEAR' to clear out all saved expenses" + "\n");
        System.out.println("'" + EXIT + "' or 'EXIT' to save changes then exit the expense manager" + "\n");

        askForCommand();
        launch(scanner);
    }

    /**
     * Add an expense to the logs.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * */

    private static void addExpense(Scanner scanner) {
        System.out.print("\nEnter the amount spent: ");
        int amount;
        try {
            amount = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\"Amount spent\" is my way of asking for a numerical value ok?\n");

            askForCommand();

            scanner.nextLine();
            launch(scanner);

            return;
        }

        while (amount < 1) {
            if (amount == NUM_CANCEL) {
                System.out.println();
                askForCommand();
                launch(scanner);
                return;
            }

            System.out.println("\nExpense has to be more than that, come on. Type '-1' to cancel");
            amount = scanner.nextInt();
        }

        scanner.nextLine();

        System.out.print("Description for this expense: ");
        String description = scanner.nextLine();

        checkIfBlank(description, "Expense entry must have a description", scanner);

        Manager.increaseAmountSpent(new Expense(amount, description));

        System.out.println();
        askForCommand();
        launch(scanner);
    }

    /**
     * Tell the user that the command entered is invalid.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * @param command recognised command.
     * */

    private static void unknownCommand(String command, Scanner scanner) {
        System.out.println("\n'" + command + "' is not a registered command, type 'HELP' or 'H' for a list of registered commands.");
        System.out.println();
        askForCommand();
        launch(scanner);
    }

    /**
     * Check to see if the description passed is blank and keep asking until one is given or the operation is cancelled.
     *
     * @since 1.0
     * @param scanner scanner object to take user input.
     * */

    private static void checkIfBlank(String text, String printIfBlank, Scanner scanner) {
        while (text.isBlank()) {
            System.out.println(printIfBlank + ", enter one now otherwise type 'C' or 'CANCEL' to cancel");

            text = scanner.nextLine();

            if (text.equalsIgnoreCase("CANCEL") || text.equalsIgnoreCase("C")) {
                askForCommand();
                launch(scanner);
                return;
            }
        }
    }

    /**
     * Ask the user to input a command
     *
     * @since 1.0
     * */

    private static void askForCommand() {
        System.out.println("Type a command to continue");
    }
}