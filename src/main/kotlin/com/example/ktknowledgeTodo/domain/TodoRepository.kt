package com.example.ktknowledgeTodo.domain

interface TodoRepository {
    fun create(todo: Todo): Todo
}
