package pl.pw.ekartapacjenta

import DummyData
import DummyDataEKG
import DummyDataMorf
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import model.Credentials
import model.PatientDataResponse
import model.Role
import pl.pw.ekartapacjenta.repository.EKGMeasurementRepository
import pl.pw.ekartapacjenta.repository.MorfMeasurementRepository
import pl.pw.ekartapacjenta.repository.TemperatureMeasurementRepository
import pl.pw.ekartapacjenta.repository.UserRepository
import java.util.*

fun Application.configureRouting() {
    val userRepo = UserRepository()
    val ekgRepo = EKGMeasurementRepository()
    val morfRepo = MorfMeasurementRepository()
    val tempRepo = TemperatureMeasurementRepository()

    launch {
        if (userRepo.isEmpty()) {
            userRepo.insert(DummyData.user1)
            userRepo.insert(DummyData.doctor1)

            ekgRepo.insert(DummyDataEKG.ekgMeasurement1)
            tempRepo.insert(DummyData.temperatureMeasurements1)
            morfRepo.insert(DummyDataMorf.morfMeasurements1)
        }
    }

    routing {
        authenticate("auth-jwt") {
            get("/patient/{id}/info") {
                val patientId = call.parameters["id"]
                val principal = call.principal<JWTPrincipal>()
                val principalId = principal!!.payload.getClaim("id").asString()
                val user = userRepo.findById(UUID.fromString(principalId))!!

                if (user.id.toString() != patientId &&
                    user.role != Role.DOCTOR) {
                    call.respond(HttpStatusCode.Unauthorized, "Autentykacja nie powiodła się")
                    return@get
                }

                val patient = userRepo.findById(UUID.fromString(patientId))
                if (patient == null) {
                    call.respond(HttpStatusCode.NotFound, "Nie znaleziono pacjenta")
                    return@get
                }
                val temperatureMeasurements = tempRepo.temperatureMeasurements(patient.id)
                val morfMeasurements = morfRepo.morfMeasurements(patient.id)
                val ekgMeasurements = ekgRepo.ekgMeasurements(patient.id)

                call.respond(
                    PatientDataResponse(
                        patient,
                        temperatureMeasurements,
                        morfMeasurements,
                        ekgMeasurements
                    )
                )
            }
        }
        get("/") {
            call.respondText("Ekarta pacjenta")
        }
        post("/login") {
            val credentials = call.receive<Credentials>()

            val user = userRepo.findByLogin(credentials.username)

            if (user == null || user.password != credentials.password) {
                call.respond(HttpStatusCode.Unauthorized, "Autentykacja nie powiodła się")
                return@post
            }

            val token = application.generateToken(user)

            call.respond(hashMapOf(
                "id" to user.id.toString(),
                "token" to token
            ))
        }
        get(".well-known/jwks.json") {
            val jwks = """
                {
                  "keys": [
                    {
                      "kty": "oct",
                      "kid": "1",
                      "alg": "HS256",
                      "k": "ZWthcnRhcGFjamVudGExMjM"
                    }
                  ]
                }
            """.trimIndent()
            call.respond(jwks)
        }
    }
}