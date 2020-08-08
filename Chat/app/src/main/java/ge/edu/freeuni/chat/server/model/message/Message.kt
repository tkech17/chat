package ge.edu.freeuni.chat.server.model.message

import java.time.LocalDateTime

data class Message(
    val src: String,
    val dst: String,
    val text: String,
    val date: LocalDateTime
)