package com.michalbaran.incomeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michalbaran.incomeapp.database.Income;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class IncomeViewAdapter extends RecyclerView.Adapter<IncomeViewAdapter.ViewHolder> {

    private Context context;
    private  List<Income> incomeList;

    public IncomeViewAdapter (Context context, List<Income> incomeList){
        this.context=context;
        this.incomeList=incomeList;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.income_recycler_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Income income = this.incomeList.get(position);
        holder.category.setText(income.getCategory());
        holder.date.setText(formatDate(income.getDate()));
        holder.amount.setText(income.getAmount().toString());
    }

    @Override
    public int getItemCount() {
        return this.incomeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView category, date, amount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.category = itemView.findViewById(R.id.txtRecyclerCategory);
            this.date = itemView.findViewById(R.id.txtRecyclerDate);
            this.amount = itemView.findViewById(R.id.txtRecyclerAmount);

        }
    }
}
