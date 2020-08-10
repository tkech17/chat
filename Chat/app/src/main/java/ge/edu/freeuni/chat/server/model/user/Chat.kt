package ge.edu.freeuni.chat.server.model.user

import ge.edu.freeuni.chat.server.model.message.Message
import java.io.Serializable

data class Chat(
    val id: String,
    val user1: User,
    val user2: User,
    val lastMessage: Message?
) : Serializable {

    fun messagingTo(currentUsername: String): String {
        if (user1.username == currentUsername) {
            return user2.username
        }
        return user1.username
    }

    fun getMessagingUser(currentUsername: String): User {
        if (user1.username == currentUsername) {
            return user2
        }
        return user1
    }

}