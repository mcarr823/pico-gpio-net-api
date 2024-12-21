package dev.mcarr.pgna.server.interfaces

import dev.mcarr.pgna.server.data.classes.BooleanResponse
import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.PinValue
import dev.mcarr.pgna.server.data.classes.StringResponse
import dev.mcarr.pgna.server.data.request.PinRequest
import dev.mcarr.pgna.server.data.request.PinsRequest
import java.io.Closeable

/**
 * Interface which defines the methods which a PGN server client
 * should implement.
 * */
interface IPicoGpioNetClient : Closeable {

    /**
     * Attempt to establish a connection to the PGN daemon.
     *
     * This function must be called prior to attempting any
     * reads or writes.
     * */
    suspend fun connect()

    /**
     * Retrieves the value of a single pin.
     *
     * @param req Pin to read the value of
     *
     * @return The pin and its value
     * */
    suspend fun getPin(req: PinRequest.Get): PinValue

    /**
     * Sets the state of a single pin.
     *
     * @param req The pin to change, and the new value to set it to
     *
     * @return Success status
     * */
    suspend fun setPin(req: PinRequest.Set): BooleanResponse

    /**
     * Waits for a given pin to reach a particular value before
     * continuing execution.
     *
     * @param req Pin to wait on, value to wait for, and amount of time
     * to wait between polls.
     *
     * @return Success status
     * */
    suspend fun waitForPin(req: PinRequest.Wait): BooleanResponse

    /**
     * Retrieves the value of multiple pins.
     *
     * @param req Pins to read the values of
     *
     * @return List of pins and their values
     * */
    suspend fun getPins(req: PinsRequest.Get): List<PinValue>

    /**
     * Sets the states of multiple pins.
     *
     * @param req List of pins to change, and the values to set
     * for each one.
     *
     * @return Success status
     * */
    suspend fun setPins(req: PinsRequest.Set): BooleanResponse

    /**
     * Sends raw byte data to write over SPI.
     *
     * @param data Array of bytes to write to the SPI device
     *
     * @return Success status
     * */
    suspend fun spiWrite(data: ByteArray): BooleanResponse

    /**
     * Tells the Pico server to wait for a defined amount of time
     * before moving onto the next request.
     *
     * @param millis Time to wait in milliseconds
     *
     * @return Success status
     * */
    suspend fun delay(millis: Short): BooleanResponse

    /**
     * Asks the Pico device to identify itself.
     *
     * @return Name of the device running the PGN daemon
     * */
    suspend fun getName(): StringResponse

    /**
     * Asks the Pico device which version of PGN it is running.
     *
     * @return API version of the PGN daemon running on the target device
     * */
    suspend fun getApiVersion(): IntResponse

}