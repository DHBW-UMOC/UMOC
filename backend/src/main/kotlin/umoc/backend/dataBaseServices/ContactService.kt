package umoc.backend.dataBaseServices

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Service
import umoc.backend.dataBaseEntitys.Contact
import umoc.backend.dataBaseEntitys.ContactType

@Service
class ContactService {
    fun insertContact(
        contactId: String,
        userId: String,
        status: ContactType
    ) {
        transaction {
            Contact.insert {
                it[Contact.contactId] = contactId
                it[Contact.userId] = userId
                it[Contact.status] = status
            }
        }
    }

    fun changeState(contactId: String, status: ContactType) {
        transaction {
            Contact.update({ Contact.contactId eq contactId }) {
                it[Contact.status] = status
            }
        }
    }

    fun getContacts(userId: String): List<ContactDto> {
        return transaction {
            Contact.selectAll().where { Contact.userId eq userId }.map { toContactDTO(it) }
        }
    }

    private fun toContactDTO(contact: ResultRow): ContactDto {
        return ContactDto(
            contactId = contact[Contact.contactId].toString(),
            userId = contact[Contact.userId].toString(),
            status = contact[Contact.status]
        )
    }
}

data class ContactDto(
    val contactId: String,
    val userId: String,
    val status: ContactType
)