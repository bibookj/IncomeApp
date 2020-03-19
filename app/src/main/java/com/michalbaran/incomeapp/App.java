package com.michalbaran.incomeapp;

import android.app.Application;

import com.michalbaran.incomeapp.database.AppDatabase;
import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseConfig appDatabaseConfig = new DatabaseConfig.Builder(AppDatabase.class)
                .build();

        ReActiveAndroid.init(new ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabaseConfig)
                .build());
    }

}