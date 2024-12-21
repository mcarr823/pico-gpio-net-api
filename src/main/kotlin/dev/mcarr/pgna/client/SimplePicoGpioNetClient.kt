package dev.mcarr.pgna.client

import dev.mcarr.pgna.server.data.classes.BooleanResponse
import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.PinValue
import dev.mcarr.pgna.server.data.classes.StringResponse
import dev.mcarr.pgna.server.data.request.PinRequest
import dev.mcarr.pgna.server.data.request.PinsRequest
import dev.mcarr.pgna.server.interfaces.IPicoGpioNetClient
import dev.mcarr.pgnc.PicoGpioNetClient
import java.io.Closeable

/**
 * PGN server client implementation.
 *
 * This class translates API request bodies into PGN requests
 * and sends them to the actual PGN client.
 *
 * ie. It takes data from this application's API endpoints
 * (defined by Sping Boot web annotations), then converts
 * the data to a format which the PGNC (pico-gpio-net-client)
 * library understands.
 *
 * So the process is something like:
 *
 * API endpoint -> PGN server client (this class) -> actual PGN client
 *
 * Example usage:
 *
 * ```
 * val stringResponse = SimplePicoGpioNetClient(ip, port).use{ client ->
 *     client.connect()
 *     client.getName()
 * }
 * ```
 *
 * @param ip IP address of the Pico device running
 * PGN which we want to connect to.
 * @param port Port on which PGN is running. This is
 * usually port 8080.
 *
 * @see IPicoGpioNetClient
 * */
class SimplePicoGpioNetClient(
    ip: String,
    port: Int
): Closeable, IPicoGpioNetClient {

    /**
     * Underlying PGN client which handles the actual PGN requests,
     * raw byta data conversions, etc.
     * */
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

    override suspend fun setPin(req: PinRequest.Set): BooleanResponse {
        client.setPin(
            pin = req.pin.toByte(),
            value = req.value.toByte()
        )
        val result = client.flush()
        val success = result[0]
        return BooleanResponse(success)
    }

    override suspend fun setPins(req: PinsRequest.Set): BooleanResponse {
        val pinsAndValues = req.pins.map { (pin, value) ->
            pin.toByte() to value.toByte()
        }
        client.setPins(pinsAndValues)
        val result = client.flush()
        val success = result[0]
        return BooleanResponse(success)
    }

    override suspend fun getPin(req: PinRequest.Get): PinValue {
        val pin = req.pin
        val value = client.getPin(pin = pin.toByte()).toInt()
        return PinValue(pin = pin, value = value)
    }

    override suspend fun getPins(req: PinsRequest.Get): List<PinValue> {
        val pins = req.pins.map { it.toByte() }
        val values = client.getPins(pins = pins.toByteArray())
        return req.pins.mapIndexed { i, pin ->
            val value = values[i].toInt()
            PinValue(pin = pin, value = value)
        }
    }

    override suspend fun spiWrite(data: ByteArray): BooleanResponse {
        client.spiWrite(data)
        val result = client.flush()
        val success = result[0]
        return BooleanResponse(success)
    }

    override suspend fun delay(millis: Short): BooleanResponse {
        client.delay(millis)
        val result = client.flush()
        val success = result[0]
        return BooleanResponse(success)
    }

    override suspend fun waitForPin(req: PinRequest.Wait): BooleanResponse {
        client.waitForPin(
            pin = req.pin.toByte(),
            value = req.value.toByte(),
            millis = req.millis.toShort()
        )
        val result = client.flush()
        val success = result[0]
        return BooleanResponse(success)
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