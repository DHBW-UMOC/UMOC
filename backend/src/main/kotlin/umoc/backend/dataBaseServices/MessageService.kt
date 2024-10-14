package umoc.backend.dataBaseServices

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import umoc.backend.dataBaseEntitys.Message

@Service
class MessageService {
    fun sendMessage(senderId: String, receiverId: String, message: String) {
        Message.insert {
            it[Message.senderId] = senderId
            it[Message.receiverId] = receiverId
            it[Message.message] = message
        }
    }

    fun getMessages(userId: String): List<MessageDto> {
        return transaction {
            Message.selectAll()
                .where { Message.receiverId eq userId }
                .sortedBy { it[Message.createdAt] }
                .map { toMessageDTO(it) }
        }
    }

    fun getMessages(userId: String, page: Int, pageSize: Int): List<MessageDto> {
        return transaction {
            Message.selectAll().where { Message.receiverId eq userId }
                .orderBy(Message.createdAt to SortOrder.ASC)
                .limit(pageSize, offset = ((page - 1) * pageSize).toLong())
                .map { toMessageDTO(it) }
        }
    }

    private fun toMessageDTO(message: ResultRow): MessageDto {
        return MessageDto(
            senderId = message[Message.senderId],
            receiverId = message[Message.receiverId],
            message = message[Message.message]
        )
    }
}

data class MessageDto(
    val senderId: String,
    val receiverId: String,
    val message: String
)