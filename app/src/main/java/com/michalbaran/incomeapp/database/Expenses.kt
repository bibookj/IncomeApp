package com.michalbaran.incomeapp.database

import com.reactiveandroid.Model
import com.reactiveandroid.annotation.Column
import com.reactiveandroid.annotation.PrimaryKey
import com.reactiveandroid.annotation.Table

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@Table(name = "Expenses", database = AppDatabase::class)
class Expenses : Model {

    @PrimaryKey
    val id: Long? = null

    @Column
    var category_id: Long? = null
    @Column
    var date: Date? = null
    @Column
    var amount: Double? = null

    constructor() {}

    constructor(category_id: Long?, date: Date, amount: Double?) {
        this.category_id = category_id
        this.date = date
        this.amount = amount
    }

}
