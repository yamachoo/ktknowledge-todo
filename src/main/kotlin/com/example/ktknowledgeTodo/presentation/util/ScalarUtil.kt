package com.example.ktknowledgeTodo.presentation.util

import com.expediagroup.graphql.generator.scalars.ID
import graphql.relay.Relay

fun Int.toID(type: String): ID {
    val globalId: String = Relay().toGlobalId(type, this.toString())

    return ID(globalId)
}

fun ID.toInt(): Int {
    val globalId: Relay.ResolvedGlobalId = Relay().fromGlobalId(this.toString())

    return globalId.id.trim().toInt()
}
