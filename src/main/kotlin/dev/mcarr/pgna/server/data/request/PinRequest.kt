package dev.mcarr.pgna.server.data.request

import kotlinx.serialization.Serializable

/**
 * This class defines the expected request bodies for the
 * /pin endpoint.
 *
 * Request bodies will be transferred in JSON format and
 * converted to these data classes automatically.
 * */
class PinRequest {

    /**
     * /pin/set endpoint request body.
     *
     * eg. {"pin":20, "value":1}
     *
     * @param pin GPIO pin which is going to have its value changed
     * @param value Value to set that pin to
     * */
    @Serializable
    data class Set(
        val pin: Int,
        val value: Int
    )

    /**
     * /pin/get endpoint request body.
     *
     * eg. {"pin":20}
     *
     * @param pin GPIO pin for which to retrieve its value
     * */
    @Serializable
    data class Get(
        val pin: Int
    )

    /**
     * /pin/wait endpoint request body.
     *
     * eg. {"pin":20, "value":1, "millis":100}
     *
     * @param pin Pin to wait on
     * @param value Value to wait for the pin to reach
     * @param millis Milliseconds to wait between pin reads
     * */
    @Serializable
    data class Wait(
        val pin: Int,
        val value: Int,
        val millis: Int
    )

}