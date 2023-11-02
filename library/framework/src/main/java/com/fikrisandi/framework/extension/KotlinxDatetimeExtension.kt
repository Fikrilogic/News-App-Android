package com.fikrisandi.framework.extension

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

fun Instant.format(formatString: String): String {
    val localDatetimeJava =
        this.toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern(formatString)
    return localDatetimeJava.format(formatter)
}