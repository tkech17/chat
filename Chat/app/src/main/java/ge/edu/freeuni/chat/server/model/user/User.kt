package ge.edu.freeuni.chat.server.model.user

import java.io.Serializable

data class User(
    val id: Long,
    val username: String,
    val whatIDo: String,
    val picture: String?
) : Serializable