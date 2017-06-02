package com.sap.health.wtp.router

import com.sap.health.wtp.handler.MessageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiRouter(val messageHandler: MessageHandler) {

    @Bean
    fun route() = router {
        ("/\$process-message").nest {
            POST("/", messageHandler::process)
        }
    }
}
