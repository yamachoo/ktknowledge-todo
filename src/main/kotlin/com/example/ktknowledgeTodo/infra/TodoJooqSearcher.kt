package com.example.ktknowledgeTodo.infra

import com.example.ktknowledgeTodo.domain.Todo
import com.example.ktknowledgeTodo.infra.jooq.tables.references.TODOS
import com.example.ktknowledgeTodo.infra.util.convertToOffsetDateTime
import com.example.ktknowledgeTodo.usecase.TodoSearcher
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

@Repository
class TodoJooqSearcher(private val dsl: DSLContext) : TodoSearcher {
    override fun findAll(): List<Todo> {
        val records = dsl.select()
            .from(TODOS)
            .orderBy(TODOS.ID.asc())
            .fetch()

        return records.map { it.convertToTodo() }
    }

    private fun Record.convertToTodo() = Todo(
        this.get(TODOS.ID)!!,
        this.get(TODOS.TITLE)!!,
        this.get(TODOS.DONE)!!,
        this.get(TODOS.CREATED_AT)!!.convertToOffsetDateTime(),
        this.get(TODOS.UPDATED_AT)!!.convertToOffsetDateTime(),
    )
}
