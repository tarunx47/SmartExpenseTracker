package model;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private UUID id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(UUID id, double amount, String category, String description, LocalDate date) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }
    public Expense(double amount, String category, String description, LocalDate date) {
            this.id = UUID.randomUUID();
            this.amount = amount;
            this.category = category;
            this.description = description;
            this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
