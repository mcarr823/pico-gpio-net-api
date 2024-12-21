package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

/**
 * Represents a GPIO pin and its value.
 *
 * @param pin Number of the GPIO pin
 * @param value Current value of that pin
 * */
@Serializable
data class PinValue(
    val pin: Int,
    val value: Int
)
