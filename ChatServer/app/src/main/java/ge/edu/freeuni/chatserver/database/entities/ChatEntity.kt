package ge.edu.freeuni.chatserver.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ChatEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var user11: Long,
    var user12: Long
)