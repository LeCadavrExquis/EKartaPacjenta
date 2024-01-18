package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TemperatureMeasurement(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val dateMs: Long,
    val measuredValue: Double,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
