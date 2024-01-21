package pl.pw.ekartapacjenta.repository

import model.TemperatureMeasurement
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

interface DAOTemperatureMeasurement {
    suspend fun temperatureMeasurements(userId: UUID): List<TemperatureMeasurement>?
}
class TemperatureMeasurementRepository : DAOTemperatureMeasurement {
    private fun resultRowToTemperatureMeasurement(row: ResultRow) = TemperatureMeasurement(
        row[TemperatureMeasurements.id],
        row[TemperatureMeasurements.dateMs],
        row[TemperatureMeasurements.value],
        row[TemperatureMeasurements.userId],
    )

    override suspend fun temperatureMeasurements(userId: UUID): List<TemperatureMeasurement> = DatabaseFactory.dbQuery {
        TemperatureMeasurements
            .select { TemperatureMeasurements.userId eq userId }
            .map(::resultRowToTemperatureMeasurement)
            .toList()
    }

    suspend fun insert(tempMeasurements: List<TemperatureMeasurement>) = DatabaseFactory.dbQuery {
        tempMeasurements.forEach { temperatureMeasurement ->
            TemperatureMeasurements.insert {
                it[id] = temperatureMeasurement.id
                it[dateMs] = temperatureMeasurement.dateMs
                it[value] = temperatureMeasurement.value
                it[userId] = temperatureMeasurement.userId
            }
        }
    }
}

object TemperatureMeasurements : Table() {
    val id = uuid("id")
    val dateMs = long("dateMs")
    val value = double("value")
    val userId = reference("userId", Users.id)

    override val primaryKey = PrimaryKey(id)
}