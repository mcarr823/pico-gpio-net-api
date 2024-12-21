package dev.mcarr.pgna.server.data.request

import dev.mcarr.pgna.server.data.classes.PinValue
import kotlinx.serialization.Serializable

/**
 * This class defines the expected request bodies for the
 * /pins endpoint.
 *
 * Request bodies will be transferred in JSON format and
 * converted to these data classes automatically.
 * */
class PinsRequest {

    /**
     * /pins/set endpoint request body.
     *
     * eg. {"pins":[{"pin":20, "value":1}]}
     *
     * @param pins List of GPIO pins and the values to set
     * for each pin.
     * */
    @Serializable
    data class Set(
        val pins: List<PinValue>
    )

    /**
     * /pins/get endpoint request body.
     *
     * eg. {"pins":[1,2,3]}
     *
     * @param pins List of GPIO pins for which to retrieve
     * their values.
     * */
    @Serializable
    data class Get(
        val pins: List<Int>
    )

}