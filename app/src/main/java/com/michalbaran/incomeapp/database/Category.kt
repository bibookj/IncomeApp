package com.michalbaran.incomeapp.database

import com.reactiveandroid.Model
import com.reactiveandroid.annotation.Column
import com.reactiveandroid.annotation.PrimaryKey
import com.reactiveandroid.annotation.Table

@Table(name = "Categories", database = AppDatabase::class)
class Category : Model {

    @PrimaryKey
    val id: Long? = null

    @Column
    var category: String? = null

    constructor() {

    }

    constructor(category: String) {
        this.category = category
    }
}
