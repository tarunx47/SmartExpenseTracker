import model.Expense;
import service.ExpenseManager;
import ui.ConsoleUI;

import java.time.LocalDate;
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
            System.out.println("0. Exit");

            ConsoleUI.line();
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {

                    ConsoleUI.section("ADD NEW EXPENSE");

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();

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
