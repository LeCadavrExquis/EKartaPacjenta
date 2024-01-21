package pl.pw.ekartapacjenta

import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Credentials
import model.PatientDataResponse
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import java.util.concurrent.TimeUnit

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Ekarta pacjenta")
        }
        post("login") {
            val credentials = call.receive<Credentials>()

//            // Validate credentials (you should implement your own validation logic)
//            if (credentials.username == "jetbrains" && credentials.password == "foobar") {
//                // Authentication successful
//                //call.respond(HttpStatusCode.OK, "Authentication successful")
//            } else {
//                // Authentication failed
//                call.respond(HttpStatusCode.Unauthorized, "Authentication failed")
//            }

            val secret = this@routing.environment!!.config.property("jwt.secret").getString()
            val issuer = this@routing.environment!!.config.property("jwt.issuer").getString()
            val audience = this@routing.environment!!.config.property("jwt.audience").getString()
            val myRealm = this@routing.environment!!.config.property("jwt.realm").getString()

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", credentials.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }
        authenticate("auth-jwt") {
            get("/patient/{id}/info") {
//                val principal = call.principal<JWTPrincipal>()
//                val username = principal!!.payload.getClaim("username").asString()
//                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
//                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
                call.respond(
                    PatientDataResponse(
                        DummyData.user1,
                        DummyData.temperatureMeasurements1,
                        null,
                        null
                    )
                )
            }
        }
    }
}