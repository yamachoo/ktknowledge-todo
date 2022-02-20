package com.example.ktknowledgeTodo.presentation.mutation

import com.example.ktknowledgeTodo.presentation.type.TodoType
import com.example.ktknowledgeTodo.presentation.util.toInt
import com.example.ktknowledgeTodo.usecase.CreateTodoUseCase
import com.example.ktknowledgeTodo.usecase.UpdateTodoUseCase
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import org.springframework.stereotype.Component

@Component
class TodoMutation(
    private val createTodoUseCase: CreateTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : Mutation {
    fun createTodo(createTodoInput: CreateTodoInput): TodoType {
        val todoDto = createTodoUseCase.call(createTodoInput.title)

        return TodoType(todoDto)
    }

    fun updateTodo(updateTodoInput: UpdateTodoInput): TodoType {
        val todoDto = updateTodoUseCase.call(
            updateTodoInput.id.toInt(),
            updateTodoInput.title,
            updateTodoInput.done
        )

        return TodoType(todoDto)
    }
}

data class CreateTodoInput(
    val title: String
)

data class UpdateTodoInput(
    val id: ID,
    val title: String,
    val done: Boolean
)
