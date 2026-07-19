import model.Expense;
import service.ExpenseManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {

            System.out.println("\n===== Smart Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {

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

                    System.out.println("\n✅ Expense Added Successfully!");
                }
                case 2 -> {

                    System.out.println("\n------ Expense List ------");

                    if (manager.getAllExpenses().isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {

                        for (Expense e : manager.getAllExpenses()) {
                            System.out.println(e);
                        }
                    }
                }
                case 3 -> {

                    System.out.println("Thank you for using Smart Expense Tracker!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
