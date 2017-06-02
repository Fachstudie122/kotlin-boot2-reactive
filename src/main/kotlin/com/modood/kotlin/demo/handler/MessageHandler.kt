package com.sap.health.wtp.handler

import com.sap.health.wtp.model.Person
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI

@Component
class MessageHandler {

  fun process(request: ServerRequest): Mono<ServerResponse> = request.bodyToMono<Person>().map { p -> created(URI.create(p.firstName)).build().block() }

}
