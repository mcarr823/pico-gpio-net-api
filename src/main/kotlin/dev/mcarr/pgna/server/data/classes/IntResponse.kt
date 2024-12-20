package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class IntResponse(
    val key: String,
    val value: Int
)