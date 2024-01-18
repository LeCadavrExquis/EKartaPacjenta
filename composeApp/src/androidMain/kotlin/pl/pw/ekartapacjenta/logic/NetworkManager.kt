package pl.pw.ekartapacjenta.logic

import model.TemperatureMeasurement
import java.util.UUID

class NetworkManager {
    fun validateLogin(login: String, password: String): ValidateResponse {
        if (password != "123") {
            throw Exception("Invalid password")
        }

        return ValidateResponse(
            UUID.randomUUID(),
            "example token"
        )
    }

    fun getPatientData(id: UUID): List<TemperatureMeasurement> {
        return listOf(
            TemperatureMeasurement(
                UUID.randomUUID(),
                123456789,
                36.6,
                UUID.randomUUID()
            )
        )
    }
}

data class ValidateResponse(
    val id: UUID,
    val token: String
)