package ge.edu.freeuni.chat.server.model.user

data class LoginRequest(
    val userName: String,
    val whatToDo: String,
    val imageBase64: String?
)