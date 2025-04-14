package net.druidlabs;

import net.druidlabs.expensemonitor.cmdln.CommandListener;
import net.druidlabs.expensemonitor.expenses.Expense;
import net.druidlabs.expensemonitor.values.Strings;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int amount = 9000;
        String desc = Strings.TEST_DESC;

        Expense expense = new Expense(amount, desc);
    }

    private static void runExpenseMonitor() { //Call in main method to start
        System.out.println("Expense tracker");
        System.out.println("Enter a command to get started or enter 'HELP' or 'H' for a list of commands");

        CommandListener.launch(scanner);
    }

}
