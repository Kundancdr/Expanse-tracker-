package com.example.expansetrack

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun formatDateToRedable(dateInMillies:Long): String {
        val dateFormater = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormater.format(dateInMillies)
    }

        fun formatDayMonth(dateInMillis: Long): String {
            val dateFormatter = SimpleDateFormat("dd/MMM", Locale.getDefault())
            return dateFormatter.format(dateInMillis)
        }

        fun formatToDecimalValue(d: Double): String {
            return String.format("%.2f", d)
        }


        fun getMillisFromDate(date: String): Long {
            return getMilliFromDate(date)
        }
        fun getMilliFromDate(dateFormat: String?): Long {
            var date = Date()
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            try {
                date = formatter.parse(dateFormat)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            println("Today is $date")
            return date.time
        }
    }