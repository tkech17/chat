package ge.edu.freeuni.chatserver.model

import java.util.*

data class Message(
    var id: Long = 0,
    var chatId: Long,
    var src: String,
    var dst: String,
    var text: String,
    var date: Date
)