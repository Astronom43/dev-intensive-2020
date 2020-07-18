package ru.skillbranch.devintensive.utils

import java.util.*
import java.util.concurrent.ExecutionException

object Utils {
    fun parseFullName(fullName: String?):Pair<String?,String?>{
        val parts:List<String>? = fullName?.trim()?.split(" ")?.filter { it.isNotEmpty() }
        val firsName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firsName to lastName
    }

    fun toInitials(firstName:String?,secondName:String?):String?{


        val fIitial = firstName?.trim()?.toUpperCase(Locale.ROOT)?.take(1)?:""
        val lInitial = secondName?.trim()?.toUpperCase(Locale.ROOT)?.take(1)?:""
        return if((fIitial+lInitial).isBlank()) null else fIitial+lInitial
    }

    fun transliteration(payload: String, divider:String = " "): String {
        var map: Map<String,String> = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to  "v",
        "г" to  "g",
        "д" to  "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya",
        " " to divider)
        var rez = StringBuilder()
        payload.forEach {
            if (it.isLowerCase()){
            rez.append(map["$it"])}
            else{
                var itBig = it.toLowerCase()
                var rezBig = map["$itBig"]
                rezBig = rezBig?.capitalize()
                rez.append(rezBig)
            }
        }

        return rez.toString()
    }
}