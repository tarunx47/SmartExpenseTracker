package service;

import model.Expense;
import storage.FileStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
