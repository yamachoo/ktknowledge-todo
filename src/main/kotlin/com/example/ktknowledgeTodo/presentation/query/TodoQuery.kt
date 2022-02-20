package com.example.ktknowledgeTodo.presentation.query

import com.example.ktknowledgeTodo.presentation.type.TodoType
import com.example.ktknowledgeTodo.usecase.FindTodosUseCase
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class TodoQuery(private val findTodosUseCase: FindTodosUseCase) : Query {
    fun todos(): List<TodoType> {
        val todoDtos = findTodosUseCase.call()

        return todoDtos.map { TodoType(it) }
    }
}
