package ge.edu.freeuni.chatserver.database.dao

import androidx.room.Dao
import androidx.room.Query
import ge.edu.freeuni.chatserver.database.entities.ChatEntity

@Dao
interface ChatDAO {

    @Query("SELECT * FROM ChatEntity WHERE user11Id = :userId OR user12Id = :userId")
    suspend fun getUserChat(userId: Long): List<ChatEntity>

}