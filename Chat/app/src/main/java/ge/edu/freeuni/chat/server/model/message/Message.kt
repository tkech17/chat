package ge.edu.freeuni.chat.server.model.message

import java.util.*

data class Message(
    val id: Long,
    val chatId: Long,
    val src: String,
    val dst: String,
    val text: String,
    val date: Date
)