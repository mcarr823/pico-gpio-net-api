# pico-gpio-net-api

PGNA (pico-gpio-net-api) is a Spring Boot microservice written in Kotlin.

It provides an API for interfacing with a Raspberry Pi Pico which runs the [pico-gpio-net daemon](https://github.com/mcarr823/pico-gpio-net).

PGNA acts as middleware which translates regular HTTP requests into pico-gpio-net requests and executes them on a Pico.

It is intended to sit between the client (your app) and the server (Pico device), so that your application can execute commands on the Pico device via HTTP requests.


## Examples

### /pin endpoint

Get the value of a single GPIO pin.

```curl
curl -X POST \
    -d '{"pin":1}' \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    -H "Content-Type: application/json" \
    http://localhost:8080/pin/get
# Response: {"pin":1,"value":1}
```

Set the value of a single GPIO pin.

```cURL
curl -X POST \
    -d '{"pin":1, "value":1}' \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    -H "Content-Type: application/json" \
    http://localhost:8080/pin/set
# Response: {"success":true}
```

Wait for a single GPIO pin to change to a specific value.

eg. To wait for a pin to be HIGH/LOW, or for the BUSY pin to be set.

```curl
curl -X POST \
    -d '{"pin":1, "value":1, "millis":1}' \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    -H "Content-Type: application/json" \
    http://localhost:8080/pin/wait
# Response: {"success":true}
```

### /pins endpoint

Get the values of multiple GPIO pins.

```curl
curl -X POST \
    -d '{"pins":[1,2,3]}' \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    -H "Content-Type: application/json" \
    http://localhost:8080/pins/get
# Response: [{"pin":1,"value":1},{"pin":2,"value":0},{"pin":3,"value":0}]
```

Set the values of multiple GPIO pins.

```curl
curl -X POST \
    -d '{"pins":[{"pin":1,"value":1}]}' \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    -H "Content-Type: application/json" \
    http://localhost:8080/pins/set
# Response: {"success":true}
```

### /info endpoint

Get API version

```curl
curl \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    http://localhost:8080/info/api
# Response: {"key":"api","value":2}
```

Get device name

```curl
curl \
    -H "PICO-IP: 127.0.0.1" \
    -H "PICO-PORT: 8081" \
    http://localhost:8080/info/name
# Response: {"key":"name","value":"Mock server"}
```