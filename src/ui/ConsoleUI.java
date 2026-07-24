package ui;

import model.Expense;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;


public class ConsoleUI {
    private static final String LINE =
            "==============================================";

    public static void line() {
        System.out.println(LINE);
    }

    public static void title(String title) {
        line();
        System.out.printf("%30s%n", title);
        line();
    }

    public static void section(String heading) {
        System.out.println();
        System.out.println("---------- " + heading + " ----------");
    }

    public static void success(String message) {
        System.out.println("✓ " + message);
    }

    public static void error(String message) {
        System.out.println("✗ " + message);
    }

    public static void dashboard(int totalExpenses, double totalAmount) {

        title("SMART EXPENSE TRACKER");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        System.out.println();
        System.out.println("📊 DASHBOARD");
        System.out.println();

        System.out.printf("Total Expenses : %d%n", totalExpenses);
        System.out.printf("Total Amount   : $%.2f%n", totalAmount);
        System.out.printf("Today's Date   : %s%n",
                LocalDate.now().format(formatter));

        System.out.println();

        line();
    }

    public static void displayExpenses(List<Expense> expenses) {

        section("ALL EXPENSES");

        if (expenses.isEmpty()) {
            error("No expenses found.");
            return;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MMM-yy");

        System.out.printf("%-5s %-12s %-15s %-12s %-25s%n",
                "No.",
                "Date",
                "Category",
                "Amount",
                "Description");

        System.out.println("---------------------------------------------------------------");

        double total = 0;

        int serial = 1;

        for (Expense e : expenses) {

            System.out.printf("%-5d %-12s %-15s $%-11.2f %-25s%n",
                    serial++,
                    e.getDate().format(formatter),
                    e.getCategory(),
                    e.getAmount(),
                    e.getDescription());

            total += e.getAmount();
        }

        System.out.println("---------------------------------------------------------------");

        System.out.printf("Total Records : %d%n", expenses.size());
        System.out.printf("Total Amount  : $%.2f%n", total);

        line();
        System.out.println("---------------------------------------------------------------");
    }

}
