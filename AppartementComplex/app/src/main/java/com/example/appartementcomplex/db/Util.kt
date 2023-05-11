package com.example.appartementcomplex.db

import java.text.SimpleDateFormat
import java.util.*

object Util {


    fun convertDate(time: String, date: String): Long {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.parse(date)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateFormat.parse(date).time


        when (time) {
            "10AM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 10
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "11AM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 11
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "12PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 12
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "13PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 13
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "14PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 14
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "15AM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 15
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "16PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 16
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "17PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 17
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "18PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 18
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "19PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 19
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "20PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 20
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }
            "21PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 21
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }

            "22PM" -> {
                calendar[Calendar.HOUR_OF_DAY] = 22
                calendar[Calendar.MINUTE] = 0
                return calendar.time.time
            }

        }

        return 0

    }


    fun convertToTime(millis:Long):String{
        // Create a DateFormatter object for displaying date in specified format.
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("HH")

        // Create a calendar object that will convert the date and time value in milliseconds to date.

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return formatter.format(calendar.time)

    }
}