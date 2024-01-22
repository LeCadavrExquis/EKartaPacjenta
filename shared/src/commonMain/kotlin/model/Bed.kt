package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Bed(
    val id: Int,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID?,
    @Serializable(with = UUIDSerializer::class)
    val doctorId: UUID?,
)
