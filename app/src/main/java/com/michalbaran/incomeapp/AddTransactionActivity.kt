package com.michalbaran.incomeapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.michalbaran.incomeapp.database.Category
import com.reactiveandroid.query.Select
import kotlinx.android.synthetic.main.activity_add_transaction.*

import java.util.Calendar

class AddTransactionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    internal var activityMode: Char = ' '
    internal var categoryTxt: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        val intent = intent
        //if (activityMode != null) {
        activityMode = intent.getCharExtra("ACTIVITY_MODE", '0')
        //}
        // activityMode = '0';


        when (activityMode) {
            '+' -> {

                layoutAddTransaction.background = ContextCompat.getDrawable(this, R.drawable.gradient_background_green)
                btnPlus.visibility = View.GONE
                btnMinus.visibility = View.GONE
            }
            '-' -> {
                layoutAddTransaction.background = ContextCompat.getDrawable(this, R.drawable.gradient_background_red)
                btnPlus.visibility = View.GONE
                btnMinus.visibility = View.GONE
            }

            else -> {

                btnPlus.setOnClickListener { layoutAddTransaction.background = ContextCompat.getDrawable(this, R.drawable.gradient_background_green) }

                btnMinus.setOnClickListener { layoutAddTransaction.background = ContextCompat.getDrawable(this, R.drawable.gradient_background_red) }
            }
        }

        //Toast.makeText(this, activityMode, Toast.LENGTH_SHORT).show();



        val categoryList = Select.from<Category>(Category::class.java).fetch()
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFrom(this,categoryList, android.R.layout.simple_spinner_item);
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SpinnerCategory.adapter = adapter
        SpinnerCategory.onItemSelectedListener = this


        txtDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            var dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                var month = month
                month++
                val date = "$day/$month/$year"
                txtDate.text = date
            }

            val datePickerDialog = DatePickerDialog(
                    this@AddTransactionActivity,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener,
                    year, month, day)
            datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }


        btnAdd.setOnClickListener {
            val amount = java.lang.Double.valueOf(editAmount.text.toString())
            //String category = categoryEdit).getText(.toString();
            val category = categoryTxt
            val date = txtDate.text.toString()

            val intent = Intent()
            intent.putExtra("AMOUNT", amount)
            intent.putExtra("CATEGORY", category)
            intent.putExtra("DATE", date)
            setResult(Activity.RESULT_OK, intent)
            finish()//finishing activity
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        categoryTxt = parent.getItemAtPosition(position).toString()
        Toast.makeText(this, categoryTxt, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}
