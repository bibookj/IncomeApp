package com.michalbaran.incomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtDate;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Button plusBtn, minusBtn, addBtn;
    LinearLayout layout;
    Context context;
    EditText amountEdit, categoryEdit;
    char activityMode;
    Spinner categorySpinner;
    String categoryTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        Intent intent = getIntent();
        //if (activityMode != null) {
            activityMode = intent.getCharExtra("ACTIVITY_MODE",'0');
        //}
        // activityMode = '0';

        plusBtn = findViewById(R.id.btnPlus);
        minusBtn = findViewById(R.id.btnMinus);
        layout = findViewById(R.id.layoutAddTransaction);
        context = this;

        switch (activityMode) {
            case '+': {

                layout.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_background_green));
                plusBtn.setVisibility(View.GONE);
                minusBtn.setVisibility(View.GONE);

                break;
            }
            case '-': {
                layout.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_background_red));
                plusBtn.setVisibility(View.GONE);
                minusBtn.setVisibility(View.GONE);
                break;
            }

            default: {

                plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_background_green));
                    }
                });

                minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout.setBackground((ContextCompat.getDrawable(context, R.drawable.gradient_background_red)));
                    }
                });

                break;
            }
        }

        //Toast.makeText(this, activityMode, Toast.LENGTH_SHORT).show();

        amountEdit = findViewById(R.id.editAmount);
        //categoryEdit = findViewById(R.id.editCategory);
        categorySpinner = findViewById(R.id.SpinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        txtDate = findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTransactionActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month++;
                String date = day + "/" + month + "/" + year;
                txtDate.setText(date);
            }
        };

        addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = Double.valueOf(amountEdit.getText().toString());
                //String category = categoryEdit.getText().toString();
                String category = categoryTxt;
                String date = txtDate.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("AMOUNT", amount);
                intent.putExtra("CATEGORY", category);
                intent.putExtra("DATE", date);
                setResult(RESULT_OK, intent);
                finish();//finishing activity

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoryTxt = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),categoryTxt,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
