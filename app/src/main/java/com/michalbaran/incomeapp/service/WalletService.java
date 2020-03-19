package com.michalbaran.incomeapp.service;

import com.michalbaran.incomeapp.database.Expenses;
import com.michalbaran.incomeapp.database.Income;
import com.reactiveandroid.query.Select;

public class WalletService implements IWalletService{
    @Override
    public double totalIncome() {
        return Select.from(Income.class).sum("amount");
    }

    @Override
    public double totalExpenses() {
        return Select.from(Expenses.class).sum("amount");
    }

    @Override
    public double accountBalance() {
        return totalIncome()-totalExpenses();
    }
}
