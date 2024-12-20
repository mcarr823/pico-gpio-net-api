package dev.mcarr.pgna.server.routes

import dev.mcarr.pgna.server.data.classes.BooleanResponse
import dev.mcarr.pgna.server.data.classes.PinValue
import dev.mcarr.pgna.server.data.request.PinRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pin")
class PinRoute : AbstractRoute() {

    @PostMapping("/wait")
    suspend fun wait(
        @RequestHeader headers: HttpHeaders,
        @RequestBody req: PinRequest.Wait
    ): ResponseEntity<BooleanResponse> =
        useClient(headers){ it.waitForPin(req) }

    @PostMapping("/get")
    suspend fun getPin(
        @RequestHeader headers: HttpHeaders,
        @RequestBody req: PinRequest.Get
    ): ResponseEntity<PinValue> =
        useClient(headers){ it.getPin(req) }

    @PostMapping("/set")
    suspend fun setPin(
        @RequestHeader headers: HttpHeaders,
        @RequestBody req: PinRequest.Set
    ): ResponseEntity<BooleanResponse> =
        useClient(headers){ it.setPin(req) }

}