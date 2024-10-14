package umoc.backend.dataBaseServices

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import umoc.backend.dataBaseEntitys.User
import java.util.*

@Service
class UserService {
    fun insertUser(
        userId: String,
        username: String,
        password: String,
        sessionID: UUID?
    ) {
        transaction {
            User.insert {
                it[User.userId] = userId
                it[User.username] = username
                it[User.password] = password
                it[User.sessionID] = sessionID
            }
        }
    }

    fun deleteUser(userId: String) {
        transaction {
            User.deleteWhere { User.userId eq userId }
        }
    }

    fun updateSessionId(userId: String, sessionID: UUID?) {
        transaction {
            User.update({ User.userId eq userId }) {
                it[User.sessionID] = sessionID
            }
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
            password = user[User.password],
            sessionID = user[User.sessionID],
        )
    }
}

data class UserDto(
    val userId: String,
    val username: String,
    val password: String,
    val sessionID: UUID?,
)

