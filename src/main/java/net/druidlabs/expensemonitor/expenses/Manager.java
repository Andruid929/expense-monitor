package net.druidlabs.expensemonitor.expenses;

import java.util.List;
import net.druidlabs.expensemonitor.cmdln.CommandListener;

/**
 * This class is responsible for managing the expense list such as adding, removing and clearing.
 *
 * @author Andrew Jones
 * @since 1.0
 * @version 1.0
 * @see CommandListener
 * */

public abstract class Manager {

    /**
     * Load up saved expenses if any.
     * @since 1.0
     * */

    private static final List<Expense> expenses = Expenses.getExpenses();

    /**
     * All this does is print feedback to the terminal as the actual logging is done in the {@link Expense} class.
     *
     * @param expense the expense to be added.
     * @since 1.0
     * */

    public static void increaseAmountSpent(Expense expense) {
        System.out.println("\nNew expense logged on " + expense.getDate() + " at " + expense.getTime());
    }

    /**
     * Clears all expenses from the list.
     *
     * @since 1.0
     * */

    public static void clearHistory() {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses logged");
            return;
        }

        expenses.clear();

        System.out.println("\nCleared all expenses");
    }

    /**
     * Removes a specific expense from the saved expense list.
     *
     * @param description any expense that has this description (if any) will be removed.
     * */

    public static void deleteExpense(String description) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses logged\n");
            return;
        }

        if (expenses.removeIf(expense -> expense.description().equalsIgnoreCase(description))) {
            System.out.println("Deleted expense with description \"" + description + "\"\n");
        } else {
            System.out.println("No expense found with that description\n");
        }
    }

    /**
     * Prints to the terminal all saved expenses.
     *
     * @since 1.0
     * */

    public static void getAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses logged\n");
            return;
        }

        expenses.forEach(expense -> System.out.println("Expense: " + expense.amount() + "\nDescription: " + expense.description() + "\nDate: " + expense.getDate() + "\n"));
    }

    /**
     * Prints all expenses saved during the specified month.
     *
     * @param month any expense which was logged in this month will be printed out.
     * @since 1.0
     * */

    public static void getMonthExpenses(String month) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses logged\n");
            return;
        }

        List<Expense> monthExpenses = expenses.stream().filter(expense -> expense.getMonth().substring(0, 3).equalsIgnoreCase(month.substring(0, 3))).toList();

        if (monthExpenses.isEmpty()) {
            System.out.println("No expenses logged in this month\n");
            return;
        }

        monthExpenses.forEach(expense -> System.out.println("Expense: " + expense.amount() + "\nDescription: " + expense.description() +
                "\nDate: " + expense.getDate() + " | " + expense.getTime() + "\n"));
    }

}
