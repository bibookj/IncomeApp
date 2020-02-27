package com.michalbaran.incomeapp;

public class IncomeClass {

    private String category, date, amount;

    public IncomeClass() {
    }

    public IncomeClass(String category, String date, String amount) {
        this.category = category;
        this.date = date;
        this.amount = amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
}
