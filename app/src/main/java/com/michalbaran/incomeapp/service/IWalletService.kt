package com.michalbaran.incomeapp.service

interface IWalletService {
    fun totalIncome(): Double
    fun totalExpenses(): Double
    fun accountBalance(): Double
}
