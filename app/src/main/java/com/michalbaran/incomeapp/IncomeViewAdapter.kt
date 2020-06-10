package com.michalbaran.incomeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.michalbaran.incomeapp.database.Income

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class IncomeViewAdapter(private val context: Context, private val incomeList: List<Income>) : RecyclerView.Adapter<IncomeViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.income_recycler_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val income = this.incomeList[position]
        holder.category.setText(income.getCategory())
        holder.date.text = formatDate(income.date)
        holder.amount.text = income.amount!!.toString()
    }

    override fun getItemCount(): Int {
        return this.incomeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val category: TextView
        private val date: TextView
        private val amount: TextView


        init {
            this.category = itemView.findViewById(R.id.txtRecyclerCategory)
            this.date = itemView.findViewById(R.id.txtRecyclerDate)
            this.amount = itemView.findViewById(R.id.txtRecyclerAmount)

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
