package com.michalbaran.incomeapp.database;

import com.reactiveandroid.annotation.Column;
import com.reactiveandroid.annotation.PrimaryKey;
import com.reactiveandroid.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Table(name = "Expenses", database = AppDatabase.class)

public class Expenses {

    @PrimaryKey private Long id;

    @Column
    private String category;
    @Column
    private Date date;
    @Column
    private Double amount;

    public Expenses() {
    }

    public Expenses(String category, Date date, Double amount) {
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
