package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class StringResponse(
    val key: String,
    val value: String
)