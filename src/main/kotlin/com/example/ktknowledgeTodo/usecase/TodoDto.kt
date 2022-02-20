package com.example.ktknowledgeTodo.usecase

import com.example.ktknowledgeTodo.domain.Todo
import java.time.OffsetDateTime

data class TodoDto(
    val id: Int,
    val title: String,
    val done: Boolean,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
    constructor(todo: Todo) : this(
        todo.id,
        todo.title,
        todo.done,
        todo.createdAt,
        todo.updatedAt
    )
}
