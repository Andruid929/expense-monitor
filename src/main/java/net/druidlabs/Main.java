package net.druidlabs;

import net.druidlabs.expensemonitor.cmdln.CommandListener;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Expense tracker");
        System.out.println("Enter a command to get started or enter 'HELP' or 'H' for a list of commands");


        CommandListener.launch(scanner);
    }

}
