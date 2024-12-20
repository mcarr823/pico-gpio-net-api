package dev.mcarr.pgna.server.data.request

import kotlinx.serialization.Serializable

class PinRequest {

    @Serializable
    data class Set(
        val pin: Int,
        val value: Int
    )

    @Serializable
    data class Get(
        val pin: Int
    )

    @Serializable
    data class Wait(
        val pin: Int,
        val value: Int,
        val millis: Int
    )

}