package com.example.ktknowledgeTodo.presentation.type

import com.example.ktknowledgeTodo.presentation.util.toID
import com.example.ktknowledgeTodo.usecase.TodoDto
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import java.time.OffsetDateTime

const val TODO_TYPE_GRAPHQL_NAME = "Todo"

@GraphQLName(TODO_TYPE_GRAPHQL_NAME)
data class TodoType(
    val id: ID,
    val title: String,
    val done: Boolean,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
    constructor(todoDto: TodoDto) : this(
        todoDto.id.toID(TODO_TYPE_GRAPHQL_NAME),
        todoDto.title,
        todoDto.done,
        todoDto.createdAt,
        todoDto.updatedAt
    )
}
