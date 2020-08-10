package ge.edu.freeuni.chatserver.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MessageEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var chatId: Long,
    var src: Long,
    var dst: Long,
    var text: String,
    var date: Date

)