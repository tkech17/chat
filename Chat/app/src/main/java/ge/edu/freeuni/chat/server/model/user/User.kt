package ge.edu.freeuni.chat.server.model.user

import java.io.Serializable

data class User(
    val userName: String,
    val whatToDo: String,
    val imageBase64: String?
) : Serializable