package com.example.ktknowledgeTodo

import com.example.ktknowledgeTodo.presentation.hooks.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KtknowledgeTodoApplication {
    @Bean
    fun customSchemaGeneratorHooks() = CustomSchemaGeneratorHooks()
}

fun main(vararg args: String) {
    runApplication<KtknowledgeTodoApplication>(*args)
}
