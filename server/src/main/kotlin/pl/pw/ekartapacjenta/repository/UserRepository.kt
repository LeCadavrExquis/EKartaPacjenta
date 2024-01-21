package pl.pw.ekartapacjenta.repository

import model.Role
import model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

interface DAOUser {
    suspend fun user(id: UUID): User?
}

class UserRepository : DAOUser {
    private fun resultRowToUser(row: ResultRow) = User(
        row[Users.id],
        row[Users.login],
        row[Users.password],
        row[Users.name],
        row[Users.surname],
        row[Users.role],
    )
    override suspend fun user(id: UUID): User? = DatabaseFactory.dbQuery {
        Users
            .select { Users.id eq id }
            .map(::resultRowToUser)
            .singleOrNull()
    }
}

object Users : Table() {
    val id = uuid("id")
    val password = varchar("password", 50)
    val login = varchar("login", 50)
    val name = varchar("name", 50)
    val surname = varchar("surname", 50)
    val role = enumeration<Role>("role")

    override val primaryKey = PrimaryKey(id)
}