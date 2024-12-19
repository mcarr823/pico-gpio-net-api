package dev.mcarr.pgna.client

import dev.mcarr.pgna.server.interfaces.IPicoGpioNetClient
import dev.mcarr.pgnc.PicoGpioNetClient
import java.io.Closeable

class SimplePicoGpioNetClient(
    ip: String,
    port: Int
): Closeable, IPicoGpioNetClient {

    private val client = PicoGpioNetClient(
        ip = ip,
        port = port,
        autoFlush = false
    )

    override suspend fun connect(){
        client.connect()
    }

    override fun close() {
        client.close()
    }


    override suspend fun getName(): String {
        return client.getName()
    }

    override suspend fun getApiVersion(): Int {
        return client.getApiVersion().toInt()
    }

}