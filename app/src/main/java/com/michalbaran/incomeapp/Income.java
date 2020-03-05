package com.michalbaran.incomeapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Income {

    private String category;
    private Date date;
    private Double amount;

    public Income() {
    }

    public Income(String category, Date date, Double amount) {
        this.category = category;
        this.date = date;
        this.amount = amount;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
}
