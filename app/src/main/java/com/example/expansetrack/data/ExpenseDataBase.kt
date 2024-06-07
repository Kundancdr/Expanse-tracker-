package com.example.expansetrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expansetrack.data.dao.ExpenseDao
import com.example.expansetrack.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 2, exportSchema = false)
abstract class ExpenseDataBase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        private const val DATABASE_NAME = "expense_database"

        @Volatile
        private var instance: ExpenseDataBase? = null

        fun getDatabase(context: Context): ExpenseDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed initial data when database is created
                        CoroutineScope(Dispatchers.IO).launch {
                            getDatabase(context).expenseDao().apply {
                                insertExpense(ExpenseEntity(null, "salary", 5000.0, "2021-08-01", "Salary", "Income"))
                                insertExpense(ExpenseEntity(null, "Paypal", 2000.0, "2021-08-01", "Paypal", "Income"))
                                insertExpense(ExpenseEntity(null, "Netflix", 299.0, "2021-08-01", "Netflix", "Expense"))
                                insertExpense(ExpenseEntity(null, "Starbucks", 500.50, "2021-08-01", "Starbucks", "Expense"))
                            }
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
