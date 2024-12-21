package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

/**
 * Represents a response from this application where the
 * endpoint is expected to return an int.
 *
 * @param key String used to describe the Int value.
 * eg. key="apiVersion" value=1
 * @param value The integer value returned from the endpoint.
 * */
@Serializable
data class IntResponse(
    val key: String,
    val value: Int
)