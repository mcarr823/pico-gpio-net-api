package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class PinValue(
    val pin: Int,
    val value: Int
)
