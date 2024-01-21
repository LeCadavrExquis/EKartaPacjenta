package pl.pw.ekartapacjenta.repository

import UUIDSerializer
import kotlinx.serialization.Serializable
import model.EKGMeasurement
import model.Role
import model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import java.util.*

interface DAOEKGMeasurement {
    suspend fun ekgMeasurements(userId: UUID): List<EKGMeasurement>?
}
class EKGMeasurementRepository : DAOEKGMeasurement {
    private fun resultRowToEKGMeasurement(row: ResultRow) = EKGMeasurement(
        row[EKGMeasurements.id],
        row[EKGMeasurements.dateMs],
        row[EKGMeasurements.value],
        row[EKGMeasurements.userId],
    )

    override suspend fun ekgMeasurements(userId: UUID): List<EKGMeasurement> = DatabaseFactory.dbQuery {
        EKGMeasurements
            .select { EKGMeasurements.userId eq userId }
            .map(::resultRowToEKGMeasurement)
            .toList()
    }
}

object EKGMeasurements : Table() {
    val id = uuid("id")
    val dateMs = long("dateMs")
    val value = double("value")
    val userId = reference("userId", Users.id)

    override val primaryKey = PrimaryKey(id)
}