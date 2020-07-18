package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val dTime = date.time - this.time
    return when (dTime) {
        in 0L..SECOND -> "только что"
        in SECOND + 1..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND + 1..75 * SECOND -> "минуту назад"
        in 75 * SECOND + 1..45 * MINUTE -> "${TimeUnits.MINUTE.plural((dTime/ MINUTE).toInt())} назад"
        in 45 * MINUTE + 1..75 * MINUTE -> "час назад"
        in 75 * MINUTE + 1..22 * HOUR -> "${TimeUnits.HOUR.plural((dTime/ HOUR).toInt())} назад"
        in 22 * HOUR + 1..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> "${TimeUnits.DAY.plural((dTime/ DAY).toInt())} назад"
        else -> "более года назад"
    }


}

enum class TimeUnits {
    SECOND {
        override fun plural(i: Int): String {
            val rez: Array<String> = arrayOf("секунду", "секунды", "секунд")
            return pluralHelper(i, rez)
        }
    },
    MINUTE {
        override fun plural(i: Int): String {
            val rez: Array<String> = arrayOf("минуту", "минуты", "минут")
            return pluralHelper(i, rez)
        }
    },
    HOUR {
        override fun plural(i: Int): String {
            val rez: Array<String> = arrayOf("час", "часа", "часов")
            return pluralHelper(i, rez)
        }
    },
    DAY {
        override fun plural(i: Int): String {
            val rez: Array<String> = arrayOf("день", "дня", "дней")
            return pluralHelper(i, rez)

        }
    };

    abstract fun plural(i: Int): String

    fun pluralHelper(i: Int, rez: Array<String>): String {
        return if (i%100 in 11..19) "$i ${rez[2]}"
        else when (i%10) {
            1 -> "$i ${rez[0]}"
            in 2..4 -> "$i ${rez[1]}"
            else -> "$i ${rez[2]}"
        }
    }

}

