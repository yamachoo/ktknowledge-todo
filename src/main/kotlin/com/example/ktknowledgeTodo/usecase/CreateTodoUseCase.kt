package com.example.ktknowledgeTodo.usecase

import com.example.ktknowledgeTodo.domain.Todo
import com.example.ktknowledgeTodo.domain.TodoRepository
import org.springframework.stereotype.Service

@Service
class CreateTodoUseCase(private val todoRepository: TodoRepository) {
    fun call(title: String): TodoDto {
        val todo = todoRepository.create(Todo(title))

        return TodoDto(todo)
    }
}
