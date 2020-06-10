package com.michalbaran.incomeapp.database

import com.reactiveandroid.annotation.QueryColumn
import com.reactiveandroid.annotation.QueryModel

import java.util.Date

@QueryModel(database = AppDatabase::class)
class IncomeQueryModel {


    @QueryColumn(name = "income_id")
    var incomeId: Long = 0
    @QueryColumn(name = "income_amount")
    var incomeAmount: String? = null
    @QueryColumn(name = "income_date")
    var incomeDate: Date? = null
    @QueryColumn(name = "category_id")
    var categoryId: Long = 0
    @QueryColumn(name = "category_name")
    var categoryName: String? = null


}
