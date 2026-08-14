package service;

import model.Expense;
import storage.FileStorage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {

    private List<Expense> expenses;
    private FileStorage storage;

    public ExpenseManager() {

        storage = new FileStorage();

        expenses = storage.loadExpenses(); // it points to the list that has been just returned by loadExpenses()

    }

    // Add a new expense
    public void addExpense(Expense expense){

        expenses.add(expense);

        storage.saveExpenses(expenses);
    }

    // Return all expenses
    public List<Expense> getAllExpenses() {
        return expenses;
    }
    public int getTotalExpenses() {
        return getAllExpenses().size();
    }

    public double getTotalAmount() {

        double total = 0;

        for (Expense expense : getAllExpenses()) {
            total += expense.getAmount();
        }

        return total;
    }

    public boolean deleteExpenseByIndex(int index) {

        if (index < 0 || index >= expenses.size()) {
            return false;
        }

        expenses.remove(index);

        storage.saveExpenses(expenses);

        return true;
    }

    public boolean updateExpense(int index,
                                 double amount,
                                 String category,
                                 String description) {

        if (index < 0 || index >= expenses.size()) {
            return false;
        }

        Expense expense = expenses.get(index);

        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDescription(description);

        storage.saveExpenses(expenses);

        return true;
    }

    public List<Expense> searchByCategory(String category) {

        return expenses.stream()
                .filter(expense ->
                        expense.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Expense> searchByDate(LocalDate date) {

        return expenses.stream()
                .filter(expense ->
                        expense.getDate().equals(date))
                .toList();
    }
    public void sortExpenses(int choice) {

        switch (choice) {

            case 1 ->
                expenses.sort(Comparator.comparingDouble(Expense::getAmount));

            case 2 ->
                expenses.sort(Comparator.comparingDouble(Expense::getAmount).reversed());

            case 3 ->
                expenses.sort(Comparator.comparing(Expense::getDate));

            case 4 ->
                expenses.sort(Comparator.comparing(Expense::getDate).reversed());

            case 5 ->
                expenses.sort(Comparator.comparing(Expense::getCategory));

            default -> System.out.println("Invalid choice.");
        }
    }

    public double getHighestExpense() {

        if (expenses.isEmpty()) {
            return 0;
        }

        double highest = expenses.get(0).getAmount();

        for (Expense expense : expenses) {
            if (expense.getAmount() > highest) {
                highest = expense.getAmount();
            }
        }

        return highest;
    }

    public double getLowestExpense() {

        if (expenses.isEmpty()) {
            return 0;
        }

        double lowest = expenses.get(0).getAmount();

        for (Expense expense : expenses) {
            if (expense.getAmount() < lowest) {
                lowest = expense.getAmount();
            }
        }

        return lowest;
    }

    public double getAverageExpense() {

        if (expenses.isEmpty()) {
            return 0;
        }

        return getTotalAmount() / expenses.size();
    }

    public Map<String, Double> getMonthlySummary(int year, int month) {

        Map<String, Double> summary = new LinkedHashMap<>();

        for (Expense expense : expenses) {

            if (expense.getDate().getYear() == year &&
                expense.getDate().getMonthValue() == month) {

                String category = expense.getCategory();

                summary.put(
                        category,
                        summary.getOrDefault(category, 0.0) + expense.getAmount()
                );
            }
        }
        return summary;
    }

    public boolean exportReport() {

        File folder = new File("exports");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter("exports/expense-report.txt")) {

            writer.println("      SMART EXPENSE TRACKER REPORT");
            writer.println("-------------------------------------------------------------");
            writer.println("Date : " + LocalDate.now());
            writer.println();

            writer.printf("%-5s %-12s %-15s %-12s %-20s%n",
                    "No.",
                    "Date",
                    "Category",
                    "Amount",
                    "Description");

            writer.println("-------------------------------------------------------------");

            int count = 1;

            for (Expense expense : expenses) {

                writer.printf("%-5d %-12s %-15s $%-11.2f %-20s%n",
                        count++,
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getAmount(),
                        expense.getDescription());
            }

            writer.println();
            writer.println("-------------------------------------------------------------");

            writer.println("Total Expenses : " + getTotalExpenses());
            writer.printf("Total Amount   : $%.2f%n", getTotalAmount());

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
