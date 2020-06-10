package com.michalbaran.incomeapp.service

import com.michalbaran.incomeapp.database.Expenses
import com.michalbaran.incomeapp.database.Income
import com.reactiveandroid.query.Select

class WalletService : IWalletService {
    override fun totalIncome(): Double {
        return Select.from<Income>(Income::class.java).sum("amount").toDouble()
    }

    override fun totalExpenses(): Double {
        return Select.from<Expenses>(Expenses::class.java).sum("amount").toDouble()
    }

    override fun accountBalance(): Double {
        return totalIncome() - totalExpenses()
    }
}
