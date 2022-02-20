package com.example.ktknowledgeTodo.presentation.hooks

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLType
import java.time.OffsetDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        OffsetDateTime::class -> ExtendedScalars.DateTime
        else -> null
    }
}
