package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ValidateResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val token: String
)

@Serializable
data class PatientDataResponse(
    val user: User,
    val temperatureMeasurements: List<TemperatureMeasurement>?,
    val morfMeasurements: MorfMeasurement?,
    val ekgMeasurements: List<EKGMeasurement>?
)

@Serializable
data class Credentials(
    val username: String,
    val password: String
)