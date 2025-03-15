package net.druidlabs.expensemonitor.expenses;

import java.util.ConcurrentModificationException;
import java.util.List;

public abstract class Manager {

    private static int amountSpent;

    private static final List<Expense> expenses = Expenses.getExpenses();

    public static void increaseAmountSpent(Expense expense) {
        amountSpent += expense.amount();

        System.out.println("New expense logged on " + expense.getDate() + " at " + expense.getTime());
    }

    public static int getAmountSpent() {
        return amountSpent;
    }

    public static void clearHistory() {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses logged");
            return;
        }

        Expenses.expenses.clear();
        System.out.println("\nCleared all expenses");
    }

    public static void deleteExpense(String description) throws ConcurrentModificationException {
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

    public static void getAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses logged\n");
            return;
        }

        expenses.forEach(expense -> System.out.println("Expense: " + expense.amount() + "\nDescription: " + expense.description() + "\nDate: " + expense.getDate() + "\n"));
    }

    public static void getMonthExpenses(String month) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses logged\n");
            return;
        }

        expenses.forEach(expense -> {
            if (expense.getMonth().toLowerCase().startsWith(month.toLowerCase().substring(0, 3))) {
                System.out.println("Expense: " + expense.amount() + "\nDescription: " + expense.description() + "\nDate: " + expense.getDate() + " | " +
                        expense.getTime() + "\n");
            }
        });
    }

}
