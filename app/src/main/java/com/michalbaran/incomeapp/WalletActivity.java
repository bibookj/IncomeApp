package com.michalbaran.incomeapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.michalbaran.incomeapp.database.AppDatabase;
import com.michalbaran.incomeapp.database.Expenses;
import com.michalbaran.incomeapp.database.Income;
import com.reactiveandroid.query.Select;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class WalletActivity extends AppCompatActivity {

    TextView txtAccountBalance;
    Float balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtAccountBalance = findViewById(R.id.txtAccountBalance);
        balance = Select.from(Income.class).sum("amount") - Select.from(Expenses.class).sum("amount");
        txtAccountBalance.setText(balance.toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
