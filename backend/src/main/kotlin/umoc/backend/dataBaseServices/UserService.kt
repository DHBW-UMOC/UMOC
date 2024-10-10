package umoc.backend.dataBaseServices

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import umoc.backend.dataBaseEntitys.User

class UserService {
    fun insertUser(
        userId: String,
        username: String,
        password: String
    ) {
        transaction {
            User.insert {
                it[User.userId] = userId
                it[User.username] = username
                it[User.password] = password
            }
        }
    }

    fun deleteUser(userId: String) {
        transaction {
            User.deleteWhere { User.userId eq userId }
        }
    }

    fun deleteAllUsers() {
        transaction {
            User.deleteAll()
        }
    }

    fun getUser(userId: String): UserDto {
        return transaction {
            User.selectAll().where { User.userId eq userId }.map { toDto(it) }.first()
        }
    }

    fun toDto(user: ResultRow): UserDto {
        return UserDto(
            userId = user[User.userId],
            username = user[User.username],
            password = user[User.password]
        )
    }
}

data class UserDto(
    val userId: String,
    val username: String,
    val password: String
)

