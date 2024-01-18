package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MorfMeasurement(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val dateMs: Long,
    val htValue: Double,
    val hbValue: Double,
    val mcvValue: Double,
    val mchcValue: Double,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
