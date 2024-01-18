package pl.pw.ekartapacjenta.logic

import DummyData
import model.EKGMeasurement
import model.MorfMeasurement
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

    fun getPatientData(id: UUID): PatientDataResponse {
        return PatientDataResponse(
            DummyData.temperatureMeasurements1,
            null,
            null
        )
    }
}

data class ValidateResponse(
    val id: UUID,
    val token: String
)

data class PatientDataResponse(
    val temperatureMeasurements: List<TemperatureMeasurement>?,
    val morfMeasurements: MorfMeasurement?,
    val ekgMeasurements: EKGMeasurement?
)