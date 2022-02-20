package com.example.ktknowledgeTodo.presentation.query

import com.example.ktknowledgeTodo.presentation.type.TodoType
import com.example.ktknowledgeTodo.presentation.util.toInt
import com.example.ktknowledgeTodo.usecase.FindTodoByIdUseCase
import com.example.ktknowledgeTodo.usecase.FindTodosUseCase
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class TodoQuery(
    private val findTodosUseCase: FindTodosUseCase,
    private val findTodoByIdUseCase: FindTodoByIdUseCase
) : Query {
    fun todos(): List<TodoType> {
        val todoDtos = findTodosUseCase.call()

        return todoDtos.map { TodoType(it) }
    }

    fun todo(id: ID): TodoType? {
        val todoDto = findTodoByIdUseCase.call(id.toInt())

        return todoDto?.let { TodoType(it) }
    }
}
