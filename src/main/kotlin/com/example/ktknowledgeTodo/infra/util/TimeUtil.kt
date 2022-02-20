package com.example.ktknowledgeTodo.infra.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun OffsetDateTime.convertToLocalDateTime(): LocalDateTime {
    return this.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()
}

fun LocalDateTime.convertToOffsetDateTime(): OffsetDateTime {
    return this.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toOffsetDateTime()
}
