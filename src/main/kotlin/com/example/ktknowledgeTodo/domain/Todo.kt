package com.example.ktknowledgeTodo.domain

import java.time.OffsetDateTime

data class Todo(
    val id: Int,
    val title: String,
    val done: Boolean,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
    constructor(title: String, now: OffsetDateTime = OffsetDateTime.now()) : this(
        0,
        title,
        false,
        now,
        now
    )
}
