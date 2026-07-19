package storage;

import model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileStorage {
    private static final String FILE_NAME = "expenses.csv";

    // Save all expenses
    public void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {

                writer.write(
                        expense.getId() + "," +
                            expense.getAmount() + "," +
                            expense.getCategory() + "," +
                            expense.getDescription() + "," +
                            expense.getDate()
                );

                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Load all expenses
    public List<Expense> loadExpenses() {

        List<Expense> expenses = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                UUID id = UUID.fromString(data[0]);
                double amount = Double.parseDouble(data[1]);
                String category = data[2];
                String description = data[3];
                LocalDate date = LocalDate.parse(data[4]);

                Expense expense = new Expense(id, amount, category, description, date);

                expenses.add(expense);
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
