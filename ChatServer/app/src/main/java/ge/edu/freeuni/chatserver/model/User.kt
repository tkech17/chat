package ge.edu.freeuni.chatserver.model

data class User(
    var id: Long,
    var username: String,
    var whatIDo: String,
    var picture: String?
)