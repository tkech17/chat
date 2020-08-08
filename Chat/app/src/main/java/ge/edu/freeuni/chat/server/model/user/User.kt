package ge.edu.freeuni.chat.server.model.user

import ge.edu.freeuni.chat.server.model.message.Message

data class User(
    val userName: String,
    val whatToDo: String,
    val imageBase64: String?,
    val lastMessage: Message?
) {

    fun messagingTo() : String {
        if (lastMessage == null) {
            throw IllegalStateException()
        }

        if (lastMessage.dst == userName) {
            return lastMessage.src
        }
        return lastMessage.dst
    }

}