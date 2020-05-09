package com.michalbaran.incomeapp.database;

import com.reactiveandroid.Model;
import com.reactiveandroid.annotation.Column;
import com.reactiveandroid.annotation.PrimaryKey;
import com.reactiveandroid.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Table(name = "Incomes", database = AppDatabase.class)

public class Income extends Model {

    @PrimaryKey
    private Long id;

    @Column
    private Long category_id;
    @Column
    private Date date;
    @Column
    private Double amount;

    public Income()  {
    }

    public Income(Long category_id, Date date, Double amount)  {
        this.category_id = category_id;
        this.date = date;
        this.amount = amount;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

}
