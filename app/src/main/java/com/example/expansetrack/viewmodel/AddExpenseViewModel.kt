package com.example.expansetrack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expansetrack.data.ExpenseDataBase
import com.example.expansetrack.data.dao.ExpenseDao
import com.example.expansetrack.data.model.ExpenseEntity
import java.lang.IllegalArgumentException

class AddExpenseViewModel( val dao: ExpenseDao):ViewModel() {
    suspend fun addExpense(expenseEntity: ExpenseEntity):Boolean {

        try {
            dao.insertExpense(expenseEntity)
            return true
        }catch (ex: Throwable){
            return false
        }
    }
}
class AddExpenseViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddExpenseViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(dao)as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}