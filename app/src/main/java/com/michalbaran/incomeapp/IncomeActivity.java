package com.michalbaran.incomeapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Income> incomeList;
        incomeList = new ArrayList<>();
        incomeList.add(new Income("Food", Calendar.getInstance().getTime(),21.5));
        incomeList.add(new Income("Travel", Calendar.getInstance().getTime(),22.1));
        incomeList.add(new Income("Ship", Calendar.getInstance().getTime(),2000000.0));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new IncomeViewAdapter(this, incomeList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
