package com.example.expansetrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val amount: Double,
    val data: String,
    val category: String,
    val type: String
)


