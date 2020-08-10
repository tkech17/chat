package ge.edu.freeuni.chatserver.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ge.edu.freeuni.chatserver.database.entities.ChatEntity

@Dao
interface ChatDAO {

    @Query("SELECT * FROM ChatEntity WHERE user11Id = :userId OR user12Id = :userId")
    suspend fun getUserChat(userId: Long): List<ChatEntity>

    @Query("SELECT * FROM ChatEntity WHERE user11Id = :userId1 OR user12Id = :userId2")
    suspend fun getUsersChat(userId1: Long, userId2: Long): ChatEntity

    @Insert
    suspend fun save(chat: ChatEntity)


}