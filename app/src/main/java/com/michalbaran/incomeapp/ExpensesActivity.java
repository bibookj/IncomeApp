package com.michalbaran.incomeapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.michalbaran.incomeapp.database.Expenses;
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


public class ExpensesActivity extends AppCompatActivity {

    static final int ADD_TRANSACTION_REQUEST_CODE = 1;
    public List<Expenses> expensesList;
    public RecyclerView recyclerView;
    public ExpensesViewAdapter expensesViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpensesActivity.this, AddTransactionActivity.class);
                intent.putExtra("ACTIVITY_MODE",'-');
                startActivityForResult(intent, ADD_TRANSACTION_REQUEST_CODE);
            }
        });


        //expensesList = new ArrayList<>();
        /*expensesList.add(new Expenses("Dog", Calendar.getInstance().getTime(), 13.5));
        expensesList.add(new Expenses("Cat", Calendar.getInstance().getTime(), 2.50));
        expensesList.add(new Expenses("Food", Calendar.getInstance().getTime(), 17.4));*/

        expensesList = Select.from(Expenses.class).fetch();

        recyclerView = findViewById(R.id.expenses_recycler_view);
        expensesViewAdapter = new ExpensesViewAdapter(this, expensesList);
        recyclerView.setAdapter(expensesViewAdapter);
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
                Expenses expenses = new Expenses(category, date1, amount);
                expenses.save();
                expensesList.add (expenses);

                expensesViewAdapter.notifyDataSetChanged();

            }
            //String message=data.getStringExtra("MESSAGE");
            //textView1.setText(message);
        }
    }

}
