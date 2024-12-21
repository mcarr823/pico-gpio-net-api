package dev.mcarr.pgna.server.data.enums

/**
 * Enum class which represents the different headers expected
 * in each HTTP request to this application.
 *
 * Currently, all requests to this application require two headers:
 * the IP address of the Pico device running the PGN daemon, and
 * the port on which it is running.
 *
 * For example:
 *
 * ```
 * GET /info/name HTTP/1.1
 * Host: localhost
 * User-Agent: Mozilla/5.0
 * PICO-IP: 192.168.1.150
 * PICO-PORT: 8080
 * ```
 *
 * @param key The key to check for in the HTTP headers
 * */
enum class PicoHeader(val key: String){
    IP("PICO-IP"),
    PORT("PICO-PORT")
}