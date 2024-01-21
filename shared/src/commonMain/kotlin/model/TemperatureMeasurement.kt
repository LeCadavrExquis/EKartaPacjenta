package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TemperatureMeasurement(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val dateMs: Long,
    val value: Double,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
