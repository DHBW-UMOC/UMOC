package umoc.backend.dataBaseEntitys

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Message : UUIDTable("messages") {
    val senderId = varchar("senderId", 255).references(User.userId)
    val receiverId = varchar("receiverId", 255).references(User.userId)
    val message = text("message")
    val createdAt = datetime("created_at")
}