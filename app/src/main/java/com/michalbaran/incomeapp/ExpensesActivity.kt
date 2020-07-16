package com.michalbaran.incomeapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.michalbaran.incomeapp.database.Expenses
import com.reactiveandroid.query.Select

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.View
import kotlinx.android.synthetic.main.content_expenses.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date


class ExpensesActivity : AppCompatActivity() {
    //var expensesList: MutableList<Expenses>
    //var recyclerView: RecyclerView
    //var expensesViewAdapter: ExpensesViewAdapter
    private val expensesList: MutableList<Expenses> = Select.from<Expenses>(Expenses::class.java).fetch()
    private val expensesViewAdapter = ExpensesViewAdapter(this, expensesList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@ExpensesActivity, AddTransactionActivity::class.java)
            intent.putExtra("ACTIVITY_MODE", '-')
            startActivityForResult(intent, ADD_TRANSACTION_REQUEST_CODE)
        }


        //expensesList = new ArrayList<>();
        /*expensesList.add(new Expenses("Dog", Calendar.getInstance().getTime(), 13.5));
        expensesList.add(new Expenses("Cat", Calendar.getInstance().getTime(), 2.50));
        expensesList.add(new Expenses("Food", Calendar.getInstance().getTime(), 17.4));*/

        //expensesList = Select.from<Expenses>(Expenses::class.java).fetch()

        //var expensesViewAdapter = ExpensesViewAdapter(this, expensesList)
        expenses_recycler_view.adapter = expensesViewAdapter
        expenses_recycler_view.layoutManager = LinearLayoutManager(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == ADD_TRANSACTION_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                val date = data!!.getStringExtra("DATE")
                val amount = data.getDoubleExtra("AMOUNT", 0.0)
                val category = data.getStringExtra("CATEGORY")
                var date1: Date? = Calendar.getInstance().time
                try {
                    date1 = SimpleDateFormat("dd/MM/yyyy").parse(date!!)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                val expenses = Expenses(1L, date1!!, amount)
                expenses.save()
                expensesList.add(expenses)

                expensesViewAdapter.notifyDataSetChanged()

            }
            //String message=data.getStringExtra("MESSAGE");
            //textView1.setText(message);
        }
    }

    companion object {

        internal val ADD_TRANSACTION_REQUEST_CODE = 1
    }

}
