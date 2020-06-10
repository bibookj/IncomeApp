package com.michalbaran.incomeapp

import android.app.Application

import com.michalbaran.incomeapp.database.AppDatabase
import com.michalbaran.incomeapp.database.Category
import com.reactiveandroid.ReActiveAndroid
import com.reactiveandroid.ReActiveConfig
import com.reactiveandroid.internal.database.DatabaseConfig
import com.reactiveandroid.query.Select

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appDatabaseConfig = DatabaseConfig.Builder(AppDatabase::class.java!!)
                .build()

        ReActiveAndroid.init(ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabaseConfig)
                .build())

        val count = Select.from<Category>(Category::class.java).count()
        if (count == 0) {
            val category = Category("Car")
            val category1 = Category("Phone")
            val category2 = Category("Taxes")
            val category3 = Category("Salary")
            category.save()
            category1.save()
            category2.save()
            category3.save()

        }

    }


}