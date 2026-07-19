package service;

import model.Expense;
import storage.FileStorage;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expenses;
    private FileStorage storage;

    public ExpenseManager() {

        storage = new FileStorage();

        expenses = new ArrayList<>();
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
}
