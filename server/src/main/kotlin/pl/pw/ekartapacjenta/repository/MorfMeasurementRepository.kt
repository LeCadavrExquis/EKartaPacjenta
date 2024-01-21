package pl.pw.ekartapacjenta.repository

import model.MorfMeasurement
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

interface DAOMorfMeasurement {
    suspend fun morfMeasurements(userId: UUID): MorfMeasurement?
}
class MorfMeasurementRepository : DAOMorfMeasurement {
    private fun resultRowToMorfMeasurement(row: ResultRow) = MorfMeasurement(
        row[MorfMeasurements.id],
        row[MorfMeasurements.dateMs],
        row[MorfMeasurements.htvalue],
        "%",
        row[MorfMeasurements.hbValue],
        "g/dL",
        row[MorfMeasurements.mcvValue],
        "fL",
        row[MorfMeasurements.mchcValue],
        "%",
        row[MorfMeasurements.userId],
    )

    override suspend fun morfMeasurements(userId: UUID): MorfMeasurement? = DatabaseFactory.dbQuery {
        MorfMeasurements
            .select { MorfMeasurements.userId eq userId }
            .map(::resultRowToMorfMeasurement)
            .singleOrNull()
    }

    suspend fun insert(morfMeasurement: MorfMeasurement) = DatabaseFactory.dbQuery {
        MorfMeasurements.insert {
            it[id] = morfMeasurement.id
            it[dateMs] = morfMeasurement.time
            it[htvalue] = morfMeasurement.htValue
            it[hbValue] = morfMeasurement.hbValue
            it[mcvValue] = morfMeasurement.mcvValue
            it[mchcValue] = morfMeasurement.mchcValue
            it[userId] = morfMeasurement.userId
        }
    }
}

object MorfMeasurements : Table() {
    val id = uuid("id")
    val dateMs = long("dateMs")
    val userId = reference("userId", Users.id)
    val htvalue = double("htvalue")
    val hbValue = double("hbvalue")
    val mcvValue = double("mcvvalue")
    val mchcValue = double("mchcvalue")


    override val primaryKey = PrimaryKey(id)
}