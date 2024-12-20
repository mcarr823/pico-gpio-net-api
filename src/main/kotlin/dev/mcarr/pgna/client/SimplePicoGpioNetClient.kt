package dev.mcarr.pgna.client

import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.StringResponse
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


    override suspend fun getName(): StringResponse {
        val name = client.getName()
        return StringResponse(key = "name", value = name)
    }

    override suspend fun getApiVersion(): IntResponse {
        val apiVersion = client.getApiVersion().toInt()
        return IntResponse(key = "api", value = apiVersion)
    }

}