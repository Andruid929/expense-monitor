package net.druidlabs.expensemonitor.expenses;

import java.util.List;

public abstract class Manager {

    private static final List<Expense> expenses = Expenses.getExpenses();

    public static void increaseAmountSpent(Expense expense) {
        System.out.println("\nNew expense logged on " + expense.getDate() + " at " + expense.getTime());
    }

    public static void clearHistory() {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses logged");
            return;
        }

        expenses.clear();

        System.out.println("\nCleared all expenses");
    }

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

        List<Expense> monthExpenses = expenses.stream().filter(expense -> expense.getMonth().substring(0, 3).equalsIgnoreCase(month.substring(0, 3))).toList();

        if (monthExpenses.isEmpty()) {
            System.out.println("No expenses logged in this month\n");
            return;
        }

        monthExpenses.forEach(expense -> System.out.println("Expense: " + expense.amount() + "\nDescription: " + expense.description() +
                "\nDate: " + expense.getDate() + " | " + expense.getTime() + "\n"));
    }

}
