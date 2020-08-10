package ge.edu.freeuni.chatserver.model

import ge.edu.freeuni.chatserver.database.entities.MessageEntity

data class Chat(
    var id: Long,
    var user1: User,
    var user2: User,
    var lastMessage: MessageEntity?
)