package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MorfMeasurement(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val time: Long,
    val htValue: Double,
    val htUnit: String,
    val hbValue: Double,
    val hbUnit: String,
    val mcvValue: Double,
    val mcvUnit: String,
    val mchcValue: Double,
    val mchcUnit: String,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
