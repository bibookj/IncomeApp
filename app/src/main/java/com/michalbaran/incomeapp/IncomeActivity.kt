package com.michalbaran.incomeapp

import android.content.Intent
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.michalbaran.incomeapp.database.Income
import com.reactiveandroid.query.Select

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.View

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date


class IncomeActivity : AppCompatActivity() {
    var incomeList: MutableList<Income>
    var recyclerView: RecyclerView
    var incomeViewAdapter: IncomeViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
        //        Toolbar toolbar = findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@IncomeActivity, AddTransactionActivity::class.java)
            intent.putExtra("ACTIVITY_MODE", '+')
            startActivityForResult(intent, ADD_TRANSACTION_REQUEST_CODE)
        }


        incomeList = Select.from<Income>(Income::class.java).fetch()

        /*incomeList.add(new Income("Food", Calendar.getInstance().getTime(), 21.5));
        incomeList.add(new Income("Travel", Calendar.getInstance().getTime(), 22.1));
        incomeList.add(new Income("Ship", Calendar.getInstance().getTime(), 2000000.0));*/

        recyclerView = findViewById(R.id.recycler_view)
        incomeViewAdapter = IncomeViewAdapter(this, incomeList)
        recyclerView.adapter = incomeViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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

                val income = Income(1L, date1!!, amount)
                income.save()
                incomeList.add(income)

                incomeViewAdapter.notifyDataSetChanged()

            }
            //String message=data.getStringExtra("MESSAGE");
            //textView1.setText(message);
        }
    }

    companion object {

        internal val ADD_TRANSACTION_REQUEST_CODE = 1
    }

}
