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

//        expenses = new ArrayList<>(); // this points to the new list , which we don't want

        expenses = storage.loadExpenses(); // it points to the list that has been just returned by loadExpenses()

        System.out.println("Loaded expenses: " + expenses.size());
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
