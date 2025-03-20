/**
 * This module includes all that is needed to run the Expense monitor
 * */

module net.druidlabs.expensemonitor {
    exports net.druidlabs.expensemonitor.io;
    exports net.druidlabs.expensemonitor.expenses;
    exports net.druidlabs.expensemonitor.calendar;
    exports net.druidlabs.expensemonitor.cmdln;
    exports net.druidlabs.expensemonitor.values;

    requires java.base;

    uses java.util.List;
    uses java.util.concurrent.ExecutorService;
    uses java.util.concurrent.Future;
    uses java.io.Serializable;
    uses java.io.ObjectOutputStream;
    uses java.io.ObjectInputStream;
    uses java.time.LocalDate;
    uses java.time.LocalTime;
}