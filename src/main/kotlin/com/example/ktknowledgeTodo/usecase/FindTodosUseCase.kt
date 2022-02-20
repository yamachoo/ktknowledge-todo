package com.example.ktknowledgeTodo.usecase

import org.springframework.stereotype.Service

@Service
class FindTodosUseCase(private val todoSearcher: TodoSearcher) {
    fun call(): List<TodoDto> {
        val todos = todoSearcher.findAll()

        return todos.map { TodoDto(it) }
    }
}
