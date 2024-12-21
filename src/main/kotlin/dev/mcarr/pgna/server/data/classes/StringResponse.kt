package dev.mcarr.pgna.server.data.classes

import kotlinx.serialization.Serializable

/**
 * Represents a response from this application where the
 * endpoint is expected to return a string.
 *
 * @param key String used to describe the String value.
 * eg. key="name" value="My server name"
 * @param value The string value returned from the endpoint.
 * */
@Serializable
data class StringResponse(
    val key: String,
    val value: String
)