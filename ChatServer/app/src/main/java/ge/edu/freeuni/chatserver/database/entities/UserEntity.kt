package ge.edu.freeuni.chatserver.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var username: String,
    var whatIDo: String,
    var picture: String?

)