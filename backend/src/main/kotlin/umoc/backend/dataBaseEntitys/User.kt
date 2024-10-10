package umoc.backend.dataBaseEntitys

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import kotlinx.datetime.Clock

object User : UUIDTable("users") {
    val userId = varchar("userId", 255)
    val username = varchar("username", 255)
    val password = varchar("password", 255)
    val createdAt = timestamp("created_at").default(Clock.System.now())
}
