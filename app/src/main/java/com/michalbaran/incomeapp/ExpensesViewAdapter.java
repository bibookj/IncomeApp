package com.michalbaran.incomeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michalbaran.incomeapp.database.Expenses;
import com.michalbaran.incomeapp.service.IWalletService;
import com.michalbaran.incomeapp.service.WalletService;
import com.reactiveandroid.query.Delete;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ExpensesViewAdapter extends RecyclerView.Adapter<ExpensesViewAdapter.ViewHolder> {

    private Context context;
    private  List<Expenses> expensesList;

    public ExpensesViewAdapter (Context context, List<Expenses> expensesList){
        this.context=context;
        this.expensesList=expensesList;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.expenses_recycler_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Expenses expenses;
        expenses = this.expensesList.get(position);
        holder.category.setText(expenses.getCategory());
        holder.date.setText(formatDate(expenses.getDate()));
        holder.amount.setText(expenses.getAmount().toString());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete.from(Expenses.class).where("id = ?", expenses.getId()).execute();
                notifyDataSetChanged();

            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.expensesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView category, date, amount;
        private ImageButton edit,delete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.category = itemView.findViewById(R.id.txtExpensesRecyclerCategory);
            this.date = itemView.findViewById(R.id.txtExpensesRecyclerDate);
            this.amount = itemView.findViewById(R.id.txtExpensesRecyclerAmount);
            this.edit = itemView.findViewById(R.id.btnExpensesRecyclerEdit);
            this.delete = itemView.findViewById(R.id.btnExpensesRecyclerDelete);

        }
    }
}
