package dev.mcarr.pgna.server.data.request

import dev.mcarr.pgna.server.data.classes.PinValue
import kotlinx.serialization.Serializable

class PinsRequest {

    @Serializable
    data class Set(
        val pins: List<PinValue>
    )

    @Serializable
    data class Get(
        val pins: List<Int>
    )

}