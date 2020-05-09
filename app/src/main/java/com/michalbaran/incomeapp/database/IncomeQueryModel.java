package com.michalbaran.incomeapp.database;

import com.reactiveandroid.annotation.QueryColumn;
import com.reactiveandroid.annotation.QueryModel;

import java.util.Date;

@QueryModel(database = AppDatabase.class)

public class IncomeQueryModel {


    @QueryColumn(name = "income_id")
    public long incomeId;
    @QueryColumn(name = "income_amount")
    public String incomeAmount;
    @QueryColumn(name = "income_date")
    public Date incomeDate;
    @QueryColumn(name = "category_id")
    public long categoryId;
    @QueryColumn(name = "category_name")
    public String categoryName;


}
