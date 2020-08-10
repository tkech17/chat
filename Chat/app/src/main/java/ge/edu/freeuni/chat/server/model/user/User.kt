package ge.edu.freeuni.chat.server.model.user

import ge.edu.freeuni.chat.server.model.message.Message

data class User (
    val userName: String,
    val whatToDo: String,
    val imageBase64: String?
)