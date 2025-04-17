# A "few" words
Java's front end is somewhat boring...
No, DEFINITELY NOT complex and unappealing.
It's just boring.
This project is what happens when an aspiring backend developer
(beginner) with subpar front end skills decides to do a project.
Using commands to run stuff is actually fun, new direction for projects.
I wonder if incorporating Databases would be a fun thing...
Something to consider...
Enjoy!

# Launching
This program is command based so you'll need a terminal to enter commands and get feedback.

Once you have the project file set up and have the jar file, place the file anywhere you want, even there
in the downloads folder.

Now, you either open the current directory in your terminal or
open the terminal and navigate to the folder with commands.
Once you're in the folder containing the jar file, run `java -jar expense-monitor-1.0.jar`.
If your jar file has a different name, make sure that name is what you pass in.

## Commands
for (String command : List<String...
Whoops sorry, for each command listed here, typing the full command or the first letter should get you sorted.
If at any time during a prompt you want to cancel, `CANCEL` will do the trick, it has to be in full though.
Commands are not case-sensitive

### Help
`HELP`
This command will show you all the commands you can use with this program, simple enough yeah?

### Adding a new expense
`ADD`
This will first ask you for the amount spent, the value here obviously has to be numerical, and it can't be negative unless it's `-1` which will cancel adding this expense.
You will then be asked to provide a description for this expense. This description is what you give when deleting expenses, so try your best to make each one unique, yeah?
If both these inputs are valid, you'll get a confirmation that the expense has been logged.

### Removing saved expenses
`REMOVE`
You will be asked to give the description of the expense you want to remove, if any match, you'll get a confirmation of its removal.

### Getting all saved expenses
`GET ALL`
This, as the command name suggests, will give all the expenses saved with their corresponding dates regardless of the month they were logged in.

### Getting saved expenses within a specific month
`MON SUM`
Similar to the `GET ALL` command, this one will give all expenses logged but only in the month you specify if any. Expenses given from this command will also include the exact time 
the expense was logged.

### Getting the total amount of money spent
`TOTAL`
This will give the all-time total of expenses saved. 

### Clearing all expenses
`CLEAR`
The proper way of erasing all saved expenses.

### Exiting the manager
`EXIT`
It's been fun, goodbye. Any changes made prior to exiting will only be in memory and will only be saved when you use this command, so if your device shuts down, any changes made won't be saved to storage. After 3 seconds, saving will be completed and the program should automatically close.

## How saving expenses works
I did my best to ensure that saving worked across all operating systems, I mean, write once run anywhere right?
Saving only happens when exiting the manager, so that's with the exit command `E` or `EXIT`. Closing the terminal or exiting any other way **WILL NOT** save your changes.
Upon exit for the first time, the program will attempt to create a save folder in your `Documents` folder within which the program will create an `Expenses.esp` file where all the saving happens.
Unless you want to clean out all your logged expenses, there's a command for that by the way, do not tamper with this file.
