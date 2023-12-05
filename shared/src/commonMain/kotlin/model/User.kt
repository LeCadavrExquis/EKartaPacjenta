package model

import UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val login: String,
    val name: String,
    val surname: String,
    val role: Role,
)

enum class Role {
    PATIENT, DOCTOR
}
