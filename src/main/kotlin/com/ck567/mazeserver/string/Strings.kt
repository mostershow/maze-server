package com.ck567.mazeserver.string

import java.util.regex.Matcher
import java.util.regex.Pattern


inline fun StringBuilder.isEmptyAfterTrim(): Boolean{
    return this.toString().replace("\n", "").trim().isEmpty()
}

fun StringBuilder.isNotEmptyAfterTrim(): Boolean = !isEmptyAfterTrim()

fun getParamByUrl(url: String?, name: String): String {
    var url = url
    url += "&"
    val pattern = "(\\?|&){1}#{0,1}$name=[a-zA-Z0-9]*(&{1})"
    val r: Pattern = Pattern.compile(pattern)
    val matcher: Matcher = r.matcher(url)
    return if (matcher.find()) {
        matcher.group(0).split("=")[1].replace("&", "")
    } else {
        ""
    }
}
