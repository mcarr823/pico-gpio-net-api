package dev.mcarr.pgna.server.interfaces

import dev.mcarr.pgna.server.data.classes.BooleanResponse
import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.PinValue
import dev.mcarr.pgna.server.data.classes.StringResponse
import dev.mcarr.pgna.server.data.request.PinRequest
import dev.mcarr.pgna.server.data.request.PinsRequest
import java.io.Closeable

interface IPicoGpioNetClient : Closeable {

    suspend fun connect()

    suspend fun getPin(req: PinRequest.Get): PinValue
    suspend fun setPin(req: PinRequest.Set): BooleanResponse
    suspend fun waitForPin(req: PinRequest.Wait): BooleanResponse

    suspend fun getPins(req: PinsRequest.Get): List<PinValue>
    suspend fun setPins(req: PinsRequest.Set): BooleanResponse

    suspend fun spiWrite(data: ByteArray): BooleanResponse

    suspend fun delay(millis: Short): BooleanResponse

    suspend fun getName(): StringResponse
    suspend fun getApiVersion(): IntResponse

}