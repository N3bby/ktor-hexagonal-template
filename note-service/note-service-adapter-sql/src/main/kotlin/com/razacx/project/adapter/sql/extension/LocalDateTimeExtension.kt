package com.razacx.project.adapter.sql.extension

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toUtcJodaTime(): DateTime {
    return DateTime(atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), DateTimeZone.UTC)
}
