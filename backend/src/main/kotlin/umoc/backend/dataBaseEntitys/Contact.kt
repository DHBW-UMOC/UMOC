package umoc.backend.dataBaseEntitys

import org.jetbrains.exposed.sql.Table

object Contact : Table("contacts") {
    val contactId = varchar("contactId", 255).references(User.userId)
    val userId = varchar("userId", 255).references(User.userId)
    val status = enumeration("status", ContactType::class).default(ContactType.FRIEND)
}

enum class ContactType {
    FRIEND,
    BLOCKED,
    FAVORITE,
}