package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class BooleanResponse(
    val success: Boolean
)