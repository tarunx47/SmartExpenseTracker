import model.Expense;
import service.ExpenseManager;
import ui.ConsoleUI;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {

//            ConsoleUI.title("SMART EXPENSE TRACKER");
            ConsoleUI.dashboard(
                    manager.getTotalExpenses(),
                    manager.getTotalAmount()
            );

            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Edit Expense");
            System.out.println("5. Search Expenses");
            System.out.println("6. Sort Expenses");
            System.out.println("7. Statistics");
            System.out.println("8. Monthly Summary");
            System.out.println("9. Export Report");
            System.out.println("0. Exit");

            ConsoleUI.line();
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                ConsoleUI.error("Please enter a valid number.");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {

                    ConsoleUI.section("ADD NEW EXPENSE");

                    System.out.print("Enter Amount: ");
                    double amount;

                    while (true) {

                        System.out.print("Enter Amount: ");

                        if (!sc.hasNextDouble()) {
                            ConsoleUI.error("Amount must be a number.");
                            sc.nextLine();
                            continue;
                        }

                        amount = sc.nextDouble();
                        sc.nextLine();

                        if (amount <= 0) {
                            ConsoleUI.error("Amount must be greater than 0.");
                            continue;
                        }

                        break;
                    }

                    System.out.print("Enter Category: ");
                    String category;

                    while (true) {

                        System.out.print("Enter Category: ");
                        category = sc.nextLine().trim();

                        if (category.isEmpty()) {
                            ConsoleUI.error("Category cannot be empty.");
                            continue;
                        }

                        break;
                    }

                    System.out.print("Enter Description: ");
                    String description;

                    while (true) {

                        System.out.print("Enter Description: ");
                        description = sc.nextLine().trim();

                        if (description.isEmpty()) {
                            ConsoleUI.error("Description cannot be empty.");
                            continue;
                        }

                        break;
                    }

                    Expense expense = new Expense(
                            amount,
                            category,
                            description,
                            LocalDate.now()
                    );

                    manager.addExpense(expense);

                    ConsoleUI.success("Expense Added Successfully!");
                }
                case 2 -> {

                    if (manager.getAllExpenses().isEmpty()) {
                        ConsoleUI.error("Expense not found.");
                    } else {

                        ConsoleUI.displayExpenses(manager.getAllExpenses());
                    }
                }
                case 3 -> {
                    ConsoleUI.section("DELETE EXPENSE");

                    if (manager.getAllExpenses().isEmpty()) {
                        ConsoleUI.error("No expenses found");
                        break;
                    }

                    ConsoleUI.displayExpenses(manager.getAllExpenses());

                    System.out.println("Enter expense number");
                    int number = sc.nextInt();

                    boolean deleted = manager.deleteExpenseByIndex(number - 1);

                    if(deleted) {
                        ConsoleUI.success("Expense deleted successfully.");
                    } else {
                        ConsoleUI.error("Invalid expense number.");
                    }
                }
                case 4 -> {

                    ConsoleUI.section("EDIT EXPENSE");

                    if (manager.getAllExpenses().isEmpty()) {
                        ConsoleUI.error("No expenses found.");
                        break;
                    }

                    ConsoleUI.displayExpenses(manager.getAllExpenses());

                    System.out.println("Enter expense number: ");
                    if (!sc.hasNextInt()) {
                        ConsoleUI.error("Please enter a valid expense number.");
                        sc.nextLine();
                        break;
                    }

                    int number = sc.nextInt();
                    sc.nextLine();


                    System.out.println("Enter new amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Enter new category: ");
                    String category = sc.nextLine();

                    System.out.println("Enter new description: ");
                    String description = sc.nextLine();
                    boolean updated = manager.updateExpense(
                            number - 1,
                            amount,
                            category,
                            description

                    );

                    if (updated) {
                        ConsoleUI.success("Expense updated successfully.");
                    } else {
                        ConsoleUI.error("Invalid expense number.");
                    }
                }
                case 5 -> {
                    ConsoleUI.section("SEARCH EXPENSE");

                    System.out.println("1. Search by Category");
                    System.out.println("2. Search by Date");
                    System.out.println("Choose option: ");

                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {

                        case 1 -> {

                            System.out.println("Enter category: ");
                            String category = sc.nextLine();

                            ConsoleUI.displayExpenses(
                                    manager.searchByCategory(category)
                            );
                        }

                        case 2 -> {

                            System.out.println("Enter date (yyyy-MM-dd): ");
                            LocalDate date;

                            while (true) {

                                System.out.print("Enter date (yyyy-MM-dd): ");

                                try {
                                    date = LocalDate.parse(sc.nextLine());
                                    break;
                                } catch (Exception e) {
                                    ConsoleUI.error("Invalid date format.");
                                }
                            }

                            ConsoleUI.displayExpenses(
                                    manager.searchByDate(date)
                            );
                        }

                        default -> ConsoleUI.error("Invalid option.");
                    }
                }
                case 6 -> {

                    ConsoleUI.section("SORT EXPENSES");

                    System.out.println("1. Amount (Low -> High)");
                    System.out.println("2. Amount (High -> Low)");
                    System.out.println("3. Date (Oldest -> Newest)");
                    System.out.println("4. Date (Newest -> Oldest)");
                    System.out.println("5. Category (A -> Z)");
                    System.out.println("Choose option: ");

                    int option = sc.nextInt();
                    sc.nextLine();

                    manager.sortExpenses(option);

                    ConsoleUI.success("Expenses sorted successfully.");

                    ConsoleUI.displayExpenses(manager.getAllExpenses());
                }
                case 7 -> {

                    ConsoleUI.section("EXPENSE STATISTICS");

                    System.out.printf("Total Expenses  : %d%n", manager.getTotalExpenses());
                    System.out.printf("Total Amount    : $%.2f%n", manager.getTotalAmount());
                    System.out.printf("Highest Expense : $%.2f%n", manager.getHighestExpense());
                    System.out.printf("Lowest Expense  : $%.2f%n", manager.getLowestExpense());
                    System.out.printf("Average Expense : $%.2f%n", manager.getAverageExpense());

                    ConsoleUI.line();
                }
                case 8 -> {

                    ConsoleUI.section("MONTHLY SUMMARY");

                    System.out.println("Enter year : ");
                    int year = sc.nextInt();

                    System.out.println("Enter month (1-12): ");
                    int month = sc.nextInt();
                    sc.nextLine();

                    Map<String, Double> summary = manager.getMonthlySummary(year, month);

                    if (summary.isEmpty()) {
                        ConsoleUI.error("No expenses found.");
                        break;
                    }

                    double total = 0;

                    for (Map.Entry<String, Double> entry : summary.entrySet()) {

                        System.out.printf("%-15s $%.2f%n",
                                entry.getKey(),
                                entry.getValue());

                        total += entry.getValue();
                    }

                    ConsoleUI.line();
                    System.out.printf("Total          $%.2f%n", total);
                }

                case 9 -> {

                    ConsoleUI.section("EXPORT REPORT");

                    boolean exported = manager.exportReport();

                    if (exported) {
                        ConsoleUI.success("Report exported successfully.");
                        System.out.println("File: expense-report.txt");
                    } else {
                        ConsoleUI.error("Failed to export report.");
                    }
                }
                case 0 -> {

                    System.out.println("Thank you for using Smart Expense Tracker 😉\nHave a good day!");
                    sc.close();
                    return;
                }
                default -> ConsoleUI.error("Invalid choice.");
            }
        }
    }
}
