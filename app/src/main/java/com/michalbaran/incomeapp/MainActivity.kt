package com.michalbaran.incomeapp

import android.content.Intent
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        Toolbar toolbar = findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTransactionActivity::class.java)
            startActivity(intent)
        }


        btnWallet.setOnClickListener {
            val intent = Intent(this@MainActivity, WalletActivity::class.java)
            startActivity(intent)
        }

        btnIncome.setOnClickListener {
            val intent = Intent(this@MainActivity, IncomeActivity::class.java)
            startActivity(intent)
        }

        btnExpenses.setOnClickListener {
            val intent = Intent(this@MainActivity, ExpensesActivity::class.java)
            startActivity(intent)
        }
    }

}
