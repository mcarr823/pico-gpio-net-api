package dev.mcarr.pgna.server.routes

import dev.mcarr.pgna.client.SimplePicoGpioNetClient
import dev.mcarr.pgna.server.data.enums.PicoHeader
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

abstract class AbstractRoute() {

    private fun <T> badRequest() : ResponseEntity<T>{
        return ResponseEntity.badRequest().build()
    }

    private fun HttpHeaders.getOrNull(header: PicoHeader): String? {
        return this.get(header.key)?.firstOrNull()
    }

    suspend fun <T> useClient(
        headers: HttpHeaders,
        callback: suspend (client: SimplePicoGpioNetClient) -> T
    ) : ResponseEntity<T> {
        val ip = headers.getOrNull(PicoHeader.IP) ?: return badRequest()
        val port = headers.getOrNull(PicoHeader.PORT)?.toIntOrNull() ?: return badRequest()
        return useClient(ip, port, callback)
    }

    suspend fun <T> useClient(
        ip: String,
        port: Int,
        callback: suspend (client: SimplePicoGpioNetClient) -> T
    ) : ResponseEntity<T> {
        return SimplePicoGpioNetClient(ip, port).use {
            it.connect()
            val value = callback(it)
            ResponseEntity.ok(value)
        }
    }

}