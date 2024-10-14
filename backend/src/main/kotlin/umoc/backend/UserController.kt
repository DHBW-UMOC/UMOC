package umoc.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import umoc.backend.dataBaseServices.UserService

@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/api/createUser/{username}/{password}/{userId}")
    fun createUser(
        @PathVariable("username") username: String,
        @PathVariable("password") password: String,
        @PathVariable("userId") userId: String
    ) {
        println("$username, $password, $userId")
        userService.insertUser(userId, username, password, null)

        println(userService.getUser(userId))
    }

    @GetMapping("/api/deleteUser/{sessionId}/{password}")
    fun deleteUser(
        @PathVariable("sessionId") sessionId: String,
        @PathVariable("password") password: String
    ): String {
        if (userService.getUser(sessionId).password == password) {
            userService.deleteUser(sessionId)
            return "User deleted"
        }
        return "Wrong password"
    }

    @GetMapping("/api/login/{password}/{userId}")
    fun login(
        @PathVariable("password") sessionId: String,
        @PathVariable("userId") password: String
    ): String {
        return "login"
    }

    @GetMapping("/api/logout/{sessionId}/")
    fun logout(@PathVariable("sessionId") sessionId: String): String {
        return "logout"
    }

    @GetMapping("/api/addContact/{sessionId}/{OtherUserId}")
    fun addContact(
        @PathVariable("sessionId") sessionId: String,
        @PathVariable("OtherUserId") otherUserId: String
    ): String {
        return "addContact"
    }

    @GetMapping("/api/changeContact/{sessionId}/{OtherUserId}/{status}")
    fun changeContact(
        @PathVariable("sessionId") sessionId: String,
        @PathVariable("OtherUserId") otherUserId: String,
        @PathVariable("status") status: String
    ): String {
        return "changeContact"
    }

    @GetMapping("/api/sendMessage/{sessionId}/{OtherUserId}/{message}")
    fun sendMessage(
        @PathVariable("sessionId") sessionId: String,
        @PathVariable("OtherUserId") otherUserId: String,
        @PathVariable("message") status: String
    ): String {
        return "send Message"
    }
}