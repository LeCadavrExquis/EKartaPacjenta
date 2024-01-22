package pl.pw.ekartapacjenta.logic

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import model.Bed
import model.Credentials
import model.PatientDataResponse
import model.ValidateResponse

class NetworkManager(
    private var token: String?,
) {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    val DOMAIN = "http://192.168.1.33:8080"
    suspend fun validateLogin(login: String, password: String): ValidateResponse {

        val response = client.post("$DOMAIN/login") {
            contentType(ContentType.Application.Json)
            setBody(
                Credentials(
                    login,
                    password
                )
            )
        }

        if (response.status != HttpStatusCode.OK) {
            throw Exception("Invalid password")
        }

        return response.body<ValidateResponse>()
    }

    suspend fun getPatientData(id: String): PatientDataResponse {
        val response = client.get("$DOMAIN/patient/$id/info") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }
        return response.body<PatientDataResponse>()
    }

    suspend fun getBedFromId(id: String): Bed {
        val response = client.get("$DOMAIN/bed/$id") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }

        return response.body<Bed>()
    }

    fun updateToken(token: String?) {
        this.token = token
    }
}