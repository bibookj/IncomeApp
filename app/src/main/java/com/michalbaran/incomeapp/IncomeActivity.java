package com.michalbaran.incomeapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.michalbaran.incomeapp.database.Income;
import com.reactiveandroid.query.Select;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class IncomeActivity extends AppCompatActivity {

    static final int ADD_TRANSACTION_REQUEST_CODE = 1;
    public List<Income> incomeList;
    public RecyclerView recyclerView;
    public IncomeViewAdapter incomeViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IncomeActivity.this, AddTransactionActivity.class);
                intent.putExtra("ACTIVITY_MODE",'+');
                startActivityForResult(intent, ADD_TRANSACTION_REQUEST_CODE);
            }
        });


        incomeList = Select.from(Income.class).fetch();

        /*incomeList.add(new Income("Food", Calendar.getInstance().getTime(), 21.5));
        incomeList.add(new Income("Travel", Calendar.getInstance().getTime(), 22.1));
        incomeList.add(new Income("Ship", Calendar.getInstance().getTime(), 2000000.0));*/

        recyclerView = findViewById(R.id.recycler_view);
        incomeViewAdapter = new IncomeViewAdapter(this, incomeList);
        recyclerView.setAdapter(incomeViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == ADD_TRANSACTION_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                String date = data.getStringExtra("DATE");
                Double amount = data.getDoubleExtra("AMOUNT",0);
                String category = data.getStringExtra("CATEGORY");
                Date date1=Calendar.getInstance().getTime();
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Income income = new Income(category, date1, amount);
                income.save();
                incomeList.add (income);

                incomeViewAdapter.notifyDataSetChanged();

            }
            //String message=data.getStringExtra("MESSAGE");
            //textView1.setText(message);
        }
    }

}
