package com.example.ktknowledgeTodo.infra

import com.example.ktknowledgeTodo.domain.Todo
import com.example.ktknowledgeTodo.domain.TodoRepository
import com.example.ktknowledgeTodo.infra.jooq.tables.references.TODOS
import com.example.ktknowledgeTodo.infra.util.convertToLocalDateTime
import com.example.ktknowledgeTodo.infra.util.convertToOffsetDateTime
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class TodoJooqRepository(private val dsl: DSLContext) : TodoRepository {
    @Transactional
    override fun create(todo: Todo): Todo {
        val record = dsl.insertInto(TODOS)
            .set(TODOS.TITLE, todo.title)
            .set(TODOS.DONE, todo.done)
            .set(TODOS.CREATED_AT, todo.createdAt.convertToLocalDateTime())
            .set(TODOS.UPDATED_AT, todo.updatedAt.convertToLocalDateTime())
            .returning(
                TODOS.ID,
                TODOS.TITLE,
                TODOS.DONE,
                TODOS.CREATED_AT,
                TODOS.UPDATED_AT
            )
            .fetchOne()

        return Todo(
            record?.id!!,
            record.title!!,
            record.done!!,
            record.createdAt!!.convertToOffsetDateTime(),
            record.updatedAt!!.convertToOffsetDateTime()
        )
    }

    override fun update(todo: Todo) {
        dsl.update(TODOS)
            .set(TODOS.TITLE, todo.title)
            .set(TODOS.DONE, todo.done)
            .set(TODOS.CREATED_AT, todo.createdAt.convertToLocalDateTime())
            .set(TODOS.UPDATED_AT, todo.updatedAt.convertToLocalDateTime())
            .where(TODOS.ID.eq(todo.id))
            .execute()
    }
}
