package net.druidlabs.expensemonitor.cmdln;

import net.druidlabs.expensemonitor.calendar.TimeFunctions;
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

public abstract class CommandListener {

    public static void launch(Scanner scanner) {
        String command = scanner.next();

        switch (command.toUpperCase()) {
            case "H", "HELP" -> printCommandList(scanner);
            case "A", "ADD" -> addExpense(scanner);
            case "E", "EXIT" -> exitingMessage();
            case "M", "MON SUM" -> monthSummary(scanner);
            case "R", "REMOVE" -> removeExpense(scanner);
            case "T", "TOTAL" -> totalSpent(scanner);
            case "G", "GET ALL" -> allLoggedExpenses(scanner);
            case "C", "CLEAR" -> cleanExpenses(scanner);
            default -> unknownCommand(command, scanner);
        }
    }

    private static void cleanExpenses(Scanner scanner) {
        Manager.clearHistory();

        System.out.println();
        askForCommand();
        launch(scanner);
    }

    private static void allLoggedExpenses(Scanner scanner) {
        System.out.println();
        Manager.getAllExpenses();

        askForCommand();
        launch(scanner);
    }

    private static void totalSpent(Scanner scanner) {
        System.out.println("\nTotal spent: " + Expenses.getTotalAmountSpent() + "\n");

        askForCommand();
        launch(scanner);
    }

    private static void exitingMessage() {
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

    private static void monthSummary(Scanner scanner) {
        System.out.println("\nWhich month's expenses do you want to get?");

        String month = scanner.next();

        checkIfBlank(month, "Month is required", scanner);

        if (month.length() < 3) {
            System.out.println("A month name should start with at least 3 letters\n");
            askForCommand();
            launch(scanner);
        }

        boolean isExistingMonth = TimeFunctions.getMonthsOfYearShort().stream().anyMatch(s -> month.substring(0, 3).toUpperCase().equalsIgnoreCase(s));

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

    private static void unknownCommand(String command, Scanner scanner) {
        System.out.println("\n'" + command + "' is not a registered command, type 'HELP' or 'H' for a list of registered commands.");
        System.out.println();
        askForCommand();
        launch(scanner);
    }

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

    private static void askForCommand() {
        System.out.println("Type a command to continue");
    }
}