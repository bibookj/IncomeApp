package com.michalbaran.incomeapp;

import android.app.Application;

import com.michalbaran.incomeapp.database.AppDatabase;
import com.michalbaran.incomeapp.database.Category;
import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;
import com.reactiveandroid.query.Select;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseConfig appDatabaseConfig = new DatabaseConfig.Builder(AppDatabase.class)
                .build();

        ReActiveAndroid.init(new ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabaseConfig)
                .build());

        int count = Select.from(Category.class).count();
        if(count == 0){
            Category category = new Category("Car");
            Category category1 = new Category("Phone");
            Category category2 = new Category("Taxes");
            Category category3 = new Category("Salary");
            category.save();
            category1.save();
            category2.save();
            category3.save();

        }

    }


}