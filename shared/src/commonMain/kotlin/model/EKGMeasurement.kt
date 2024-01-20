package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EKGMeasurement(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val timeMs: Long,
    val arrayValue: List<Double>,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
