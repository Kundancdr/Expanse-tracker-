package com.example.expansetrack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expansetrack.R
import com.example.expansetrack.Utils
import com.example.expansetrack.data.ExpenseDataBase
import com.example.expansetrack.data.dao.ExpenseDao
import com.example.expansetrack.data.model.ExpenseEntity
import java.lang.IllegalArgumentException

class HomeViewModel(dao: ExpenseDao):ViewModel() {
    val expense = dao.getAllExpense()
    fun getBalance(list: List<ExpenseEntity>): String {
        var balance = 0.0
        for (expense in list) {
            if (expense.type == "Income") {
                balance += expense.amount
            } else {
                balance -= expense.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(balance)}"
    }

    fun getTotalExpense(list: List<ExpenseEntity>): String {
        var total = 0.0
        for (expense in list) {
            total += expense.amount
        }

        return "$ ${Utils.formatToDecimalValue(total)}"
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String {
        var totalIncome = 0.0
        for (expense in list) {
            if (expense.type == "Income") {
                totalIncome += expense.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(totalIncome)}"
    }
    fun getItemIcon(item:ExpenseEntity):Int{
         if (item.category == "Paypal"){
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix"){
            return R.drawable.ic_netflix
        } else if (item.category == "Starbucks"){
            return R.drawable.ic_starbucks
        }
        return R.drawable.ic_upwork
    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}