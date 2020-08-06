package ge.edu.freeuni.chat.server.model.user

data class User(
    val userName: String,
    val whatToDo: String,
    val imageBase64: String?
)