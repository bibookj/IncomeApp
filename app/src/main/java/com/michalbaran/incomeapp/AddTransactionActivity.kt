package com.michalbaran.incomeapp

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

import java.util.Calendar

class AddTransactionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    internal var txtDate: TextView
    internal var dateSetListener: DatePickerDialog.OnDateSetListener
    internal var plusBtn: Button
    internal var minusBtn: Button
    internal var addBtn: Button
    internal var layout: LinearLayout
    internal var context: Context
    internal var amountEdit: EditText
    internal var categoryEdit: EditText? = null
    internal var activityMode: Char = ' '
    internal var categorySpinner: Spinner
    internal var categoryTxt: String
    internal var categoryList: List<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        val intent = intent
        //if (activityMode != null) {
        activityMode = intent.getCharExtra("ACTIVITY_MODE", '0')
        //}
        // activityMode = '0';

        plusBtn = findViewById(R.id.btnPlus)
        minusBtn = findViewById(R.id.btnMinus)
        layout = findViewById(R.id.layoutAddTransaction)
        context = this

        when (activityMode) {
            '+' -> {

                layout.background = ContextCompat.getDrawable(context, R.drawable.gradient_background_green)
                plusBtn.visibility = View.GONE
                minusBtn.visibility = View.GONE
            }
            '-' -> {
                layout.background = ContextCompat.getDrawable(context, R.drawable.gradient_background_red)
                plusBtn.visibility = View.GONE
                minusBtn.visibility = View.GONE
            }

            else -> {

                plusBtn.setOnClickListener { layout.background = ContextCompat.getDrawable(context, R.drawable.gradient_background_green) }

                minusBtn.setOnClickListener { layout.background = ContextCompat.getDrawable(context, R.drawable.gradient_background_red) }
            }
        }

        //Toast.makeText(this, activityMode, Toast.LENGTH_SHORT).show();

        amountEdit = findViewById(R.id.editAmount)
        //categoryEdit = findViewById(R.id.editCategory);


        categorySpinner = findViewById(R.id.SpinnerCategory)
        categoryList = Select.from<Category>(Category::class.java).fetch()
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFrom(this,categoryList, android.R.layout.simple_spinner_item);
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter
        categorySpinner.onItemSelectedListener = this


        txtDate = findViewById(R.id.txtDate)
        txtDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                    this@AddTransactionActivity,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener,
                    year, month, day)
            datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }
        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            var month = month
            month++
            val date = "$day/$month/$year"
            txtDate.text = date
        }

        addBtn = findViewById(R.id.btnAdd)
        addBtn.setOnClickListener {
            val amount = java.lang.Double.valueOf(amountEdit.text.toString())
            //String category = categoryEdit.getText().toString();
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
        Toast.makeText(parent.context, categoryTxt, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}
