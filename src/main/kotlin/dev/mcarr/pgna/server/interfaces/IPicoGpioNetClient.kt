package dev.mcarr.pgna.server.interfaces

import java.io.Closeable

interface IPicoGpioNetClient : Closeable {

    suspend fun connect()

}