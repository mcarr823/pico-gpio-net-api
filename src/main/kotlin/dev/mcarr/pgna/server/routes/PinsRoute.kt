package dev.mcarr.pgna.server.routes

import dev.mcarr.pgna.server.data.classes.BooleanResponse
import dev.mcarr.pgna.server.data.classes.PinValue
import dev.mcarr.pgna.server.data.request.PinsRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pins")
class PinsRoute : AbstractRoute() {

    @PostMapping("/get")
    suspend fun getPins(
        @RequestHeader headers: HttpHeaders,
        @RequestBody req: PinsRequest.Get
    ): ResponseEntity<List<PinValue>> =
        useClient(headers){ it.getPins(req) }

    @PostMapping("/set")
    suspend fun setPins(
        @RequestHeader headers: HttpHeaders,
        @RequestBody req: PinsRequest.Set
    ): ResponseEntity<BooleanResponse> =
        useClient(headers){ it.setPins(req) }

}