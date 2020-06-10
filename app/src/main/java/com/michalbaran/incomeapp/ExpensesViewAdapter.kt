package com.michalbaran.incomeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.michalbaran.incomeapp.database.Expenses
import com.michalbaran.incomeapp.service.IWalletService
import com.michalbaran.incomeapp.service.WalletService

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class ExpensesViewAdapter(private val context: Context, private val expensesList: List<Expenses>) : RecyclerView.Adapter<ExpensesViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.expenses_recycler_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expenses: Expenses
        expenses = this.expensesList[position]
        holder.category.setText(expenses.getCategory())
        holder.date.text = formatDate(expenses.date)
        holder.amount.text = expenses.amount!!.toString()
        holder.id = expenses.id
    }

    override fun getItemCount(): Int {
        return this.expensesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val category: TextView
        private val date: TextView
        private val amount: TextView

        private val id: Long? = null


        init {
            this.category = itemView.findViewById(R.id.txtExpensesRecyclerCategory)
            this.date = itemView.findViewById(R.id.txtExpensesRecyclerDate)
            this.amount = itemView.findViewById(R.id.txtExpensesRecyclerAmount)

        }
    }

    companion object {

        fun formatDate(date: Date?): String {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy")
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.format(date!!)
        }
    }
}
