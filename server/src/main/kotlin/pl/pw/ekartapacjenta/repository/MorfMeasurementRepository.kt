package pl.pw.ekartapacjenta.repository

import kotlinx.serialization.Serializable
import model.MorfMeasurement
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import java.util.*

interface DAOMorfMeasurement {
    suspend fun morfMeasurements(userId: UUID): List<MorfMeasurement>?
}
class MorfMeasurementRepository : DAOMorfMeasurement {
    private fun resultRowToMorfMeasurement(row: ResultRow) = MorfMeasurement(
        row[MorfMeasurements.id],
        row[MorfMeasurements.dateMs],
        row[MorfMeasurements.htvalue],
        row[MorfMeasurements.hbValue],
        row[MorfMeasurements.mcvValue],
        row[MorfMeasurements.mchcValue],
        row[MorfMeasurements.userId],
    )

    override suspend fun morfMeasurements(userId: UUID): List<MorfMeasurement> = DatabaseFactory.dbQuery {
        MorfMeasurements
            .select { MorfMeasurements.userId eq userId }
            .map(::resultRowToMorfMeasurement)
            .toList()
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