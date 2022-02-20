package com.example.ktknowledgeTodo.presentation.mutation

import com.example.ktknowledgeTodo.presentation.type.TodoType
import com.example.ktknowledgeTodo.usecase.CreateTodoUseCase
import com.expediagroup.graphql.server.operations.Mutation
import org.springframework.stereotype.Component

@Component
class TodoMutation(private val createTodoUseCase: CreateTodoUseCase) : Mutation {
    fun createTodo(createTodoInput: CreateTodoInput): TodoType {
        val todoDto = createTodoUseCase.call(createTodoInput.title)

        return TodoType(todoDto)
    }
}

data class CreateTodoInput(
    val title: String
)
