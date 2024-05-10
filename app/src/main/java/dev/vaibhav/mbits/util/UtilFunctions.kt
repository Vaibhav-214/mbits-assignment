package dev.vaibhav.mbits.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Date) : String {
    val pattern = SimpleDateFormat("EEEE, dd MMMM", Locale.US)

    return pattern.format(date)
}

fun convertValueToAngle(value: Float): Float {
    val ratio = value / 60f
    val angle = ratio * 270f

    return if (angle > 225f) {
        585 - angle
    } else {
        225 - angle
    }
}

fun <T> List<T>.subListOrEmpty(startIndex: Int, endIndex: Int): List<T> {
    return if (this.size>= endIndex && startIndex < endIndex) {
        this.subList(startIndex, endIndex)
    } else {
        emptyList()
    }
}
