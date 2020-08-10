package ge.edu.freeuni.chatserver.model

data class LoginRequest(
    val username: String,
    val whatIDo: String,
    val imageBase64: String?
)