package com.example.ktknowledgeTodo.usecase

import com.example.ktknowledgeTodo.domain.TodoRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class UpdateTodoUseCase(
    private val todoSearcher: TodoSearcher,
    private val todoRepository: TodoRepository
) {
    fun call(id: Int, title: String, done: Boolean): TodoDto {
        val todo = todoSearcher.findOneById(id)

        checkNotNull(todo)

        val updateTodo = todo.copy(
            title = title,
            done = done,
            updatedAt = OffsetDateTime.now()
        )

        todoRepository.update(updateTodo)

        return TodoDto(updateTodo)
    }
}
