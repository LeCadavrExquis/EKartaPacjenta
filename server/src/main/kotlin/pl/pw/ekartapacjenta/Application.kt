package pl.pw.ekartapacjenta

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.*
import io.ktor.serialization.kotlinx.json.*
import pl.pw.ekartapacjenta.repository.DatabaseFactory

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()

    configureAuthentication()
    configureRouting()

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
