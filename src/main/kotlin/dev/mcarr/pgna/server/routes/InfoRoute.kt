package dev.mcarr.pgna.server.routes

import dev.mcarr.pgna.server.data.classes.IntResponse
import dev.mcarr.pgna.server.data.classes.StringResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class InfoRoute : AbstractRoute() {

    @GetMapping("/api")
    suspend fun getApiVersion(
        @RequestHeader headers: HttpHeaders
    ): ResponseEntity<IntResponse> =
        useClient(headers){ it.getApiVersion() }

    @GetMapping("/name")
    suspend fun getName(
        @RequestHeader headers: HttpHeaders
    ): ResponseEntity<StringResponse> =
        useClient(headers){ it.getName() }

}