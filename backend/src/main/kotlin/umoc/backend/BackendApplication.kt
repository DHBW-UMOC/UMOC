package umoc.backend

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import umoc.backend.dataBaseEntitys.Contact
import umoc.backend.dataBaseEntitys.Message
import umoc.backend.dataBaseEntitys.User

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/umoc",
        user = "user",
        password = "password"
    )
    transaction {
        SchemaUtils.create(User, Message, Contact)
    }
    runApplication<BackendApplication>(*args)
}
