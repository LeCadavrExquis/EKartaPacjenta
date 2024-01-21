package pl.pw.ekartapacjenta.logic

import DummyData
import model.*
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
            DummyData.user1,
            DummyData.temperatureMeasurements1,
            null,
            null
        )
    }
}