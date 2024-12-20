package dev.mcarr.pgna.server.interfaces

import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.StringResponse
import java.io.Closeable

interface IPicoGpioNetClient : Closeable {

    suspend fun connect()

    suspend fun getName(): StringResponse
    suspend fun getApiVersion(): IntResponse

}