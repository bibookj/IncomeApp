package com.michalbaran.incomeapp

import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.michalbaran.incomeapp.database.AppDatabase
import com.michalbaran.incomeapp.database.Expenses
import com.michalbaran.incomeapp.database.Income
import com.reactiveandroid.query.Select

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.content_wallet.*

class WalletActivity : AppCompatActivity() {


    internal var balance: Float? = null
    internal var totalIncome: Float? = null
    internal var totalOutcome: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        //        Toolbar toolbar = findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);


        totalIncome = Select.from<Income>(Income::class.java).sum("amount")
        totalOutcome = Select.from<Expenses>(Expenses::class.java).sum("amount")
        balance = totalIncome!! - totalOutcome!!
        txtAccountBalance.text = balance!!.toString()
        txtTotalIncome.text = totalIncome!!.toString()
        txtTotalExpenses.text = totalOutcome!!.toString()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
