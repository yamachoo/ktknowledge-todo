package com.example.ktknowledgeTodo.usecase

import org.springframework.stereotype.Service

@Service
class FindTodoByIdUseCase(private val todoSearcher: TodoSearcher) {
    fun call(id: Int): TodoDto? {
        val todo = todoSearcher.findOneById(id)

        return todo?.let { TodoDto(it) }
    }
}
