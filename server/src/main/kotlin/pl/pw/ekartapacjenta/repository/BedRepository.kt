package pl.pw.ekartapacjenta.repository

import model.Role
import model.Bed
import org.jetbrains.exposed.sql.*

interface DAOBed {
    suspend fun bed(id: Int): Bed?
}

class BedRepository : DAOBed {
    private fun resultRowToBed(row: ResultRow) = Bed(
        row[Beds.id],
        row[Beds.userId],
        row[Beds.doctorId],
    )
    suspend fun insert(bed: Bed) = DatabaseFactory.dbQuery {
        Beds.insert {
            it[id] = bed.id
            it[userId] = bed.userId
            it[doctorId] = bed.doctorId
        }
    }

    override suspend fun bed(id: Int) = DatabaseFactory.dbQuery {
        Beds
            .select { Beds.id eq id }
            .map(::resultRowToBed)
            .singleOrNull()
    }
}

object Beds : Table() {
    val id = integer("id")
    val userId = uuid("userId").nullable()
    val doctorId = uuid("doctorId").nullable()

    override val primaryKey = PrimaryKey(id)
}