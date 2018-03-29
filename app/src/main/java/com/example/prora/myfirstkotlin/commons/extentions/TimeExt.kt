package com.example.prora.myfirstkotlin.commons.extentions

import java.util.*

fun Long.getFriendlyTime(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time) / 1000).toInt()

    val sec = if (diffInSeconds >= 60) (diffInSeconds % 60) else diffInSeconds
    diffInSeconds /= 60
    val min = if (diffInSeconds >= 60) (diffInSeconds % 60) else diffInSeconds
    diffInSeconds /= 60
    val hrs = if (diffInSeconds >= 24) (diffInSeconds % 24) else diffInSeconds
    diffInSeconds /= 24
    val days = if (diffInSeconds >= 30) (diffInSeconds % 30) else diffInSeconds
    diffInSeconds /= 30
    var months = if (diffInSeconds >= 12) (diffInSeconds % 12) else diffInSeconds
    diffInSeconds /= 12
    val years = diffInSeconds

    when {
        years > 0 -> getFriendlyYear(years, sb, months)
        months > 0 -> getFriendlyMonth(months, sb, days)
        days > 0 -> getFriendlyDay(days, sb, hrs)
        hrs > 0 -> getFriendlyHour(hrs, sb, min)
        min > 0 -> getFriendlyMinute(min, sb, sec)
        else -> getFriendlySecond(sec, sb)
    }

    sb.append(" ago")

    return sb.toString()
}

private fun getFriendlyYear(years: Int, sb: StringBuffer, months: Int) {
    if (years == 1) {
        sb.append("a year")
    } else {
        sb.append("$years years")
    }
    if (years <= 6 && months > 0) {
        if (months == 1) {
            sb.append(" and a month")
        } else {
            sb.append(" and $months months")
        }
    }
}

private fun getFriendlyMonth(months: Int, sb: StringBuffer, days: Int) {
    if (months == 1) {
        sb.append("a month")
    } else {
        sb.append("$months months")
    }
    if (months <= 6 && days > 0) {
        if (days == 1) {
            sb.append(" and a day")
        } else {
            sb.append(" and $days days")
        }
    }
}

private fun getFriendlyDay(days: Int, sb: StringBuffer, hrs: Int) {
    if (days == 1) {
        sb.append("a day")
    } else {
        sb.append("$days days")
    }
    if (days <= 3 && hrs > 0) {
        if (hrs == 1) {
            sb.append(" and an hour")
        } else {
            sb.append(" and $hrs hours")
        }
    }
}

private fun getFriendlyHour(hrs: Int, sb: StringBuffer, min: Int) {
    if (hrs == 1) {
        sb.append("an hour")
    } else {
        sb.append("$hrs hours")
    }
    if (min > 1) {
        sb.append(" and $min minutes")
    }
}

private fun getFriendlyMinute(min: Int, sb: StringBuffer, sec: Int) {
    if (min == 1) {
        sb.append("a minute")
    } else {
        sb.append("$min minutes")
    }
    if (sec > 1) {
        sb.append(" and $sec seconds")
    }
}

private fun getFriendlySecond(sec: Int, sb: StringBuffer) {
    if (sec <= 1) {
        sb.append("about a second")
    } else {
        sb.append("about $sec seconds")
    }
}