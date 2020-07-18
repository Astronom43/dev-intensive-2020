package ru.skillbranch.devintensive.extensions

import org.jsoup.Jsoup

fun String.truncate(count:Int=16):String {
    val suff ="..."
    return if (this.trimEnd().length<=count) {
        this.trimEnd()
    } else{
        "${this.substring(0,count).trimEnd()}$suff"
    }

}
fun String.stripHtml():String{

    return Jsoup.parse(this).text()
}