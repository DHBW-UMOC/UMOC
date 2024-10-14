package umoc.backend.dataBaseEntitys

import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.Table

object User : Table("user") {
    val userId = varchar("userId", 255)
    val username = varchar("username", 255)
    val password = varchar("password", 255)
    val sessionID = uuid("sessionID").nullable()
    val createdAt = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(userId, name = "PK_User_ID")
}
