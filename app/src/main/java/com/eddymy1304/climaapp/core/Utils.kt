package com.eddymy1304.climaapp.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    fun formatToDayMonth(dateString: String?): String {
        if (dateString.isNullOrBlank()) return ""
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = LocalDate.parse(dateString, formatter)
        return date.format(DateTimeFormatter.ofPattern("dd MMM", Locale.getDefault()))
    }

    fun getYearMonthDay(dateString: String?): String {
        return if (dateString.isNullOrBlank()) ""
        else dateString.substring(startIndex = 0, endIndex = 10)
    }

    fun formatToHourMinute(dateString: String?): String {
        if (dateString.isNullOrBlank()) return ""
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateTime = LocalDateTime.parse(dateString, formatter)
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()))
    }

    fun getDayOfWeek(dateString: String?): String {
        if (dateString.isNullOrBlank()) return ""
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val date = LocalDate.parse(dateString, formatter)
        return date.format(DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())).uppercase()
    }

    fun getTodayFormatted(): String {
        val now = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        return now.format(formatter)
    }

}

fun <T> List<T>.average(): T? {
    return this.groupBy { it }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }?.key
}