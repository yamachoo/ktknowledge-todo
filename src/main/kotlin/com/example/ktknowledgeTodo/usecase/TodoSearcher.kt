package com.example.ktknowledgeTodo.usecase

import com.example.ktknowledgeTodo.domain.Todo

interface TodoSearcher {
    fun findAll(): List<Todo>
}
