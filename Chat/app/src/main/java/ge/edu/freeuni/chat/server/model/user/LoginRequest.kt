package ge.edu.freeuni.chat.server.model.user

data class LoginRequest(
    val username: String,
    val whatIDo: String,
    val imageBase64: String?
)