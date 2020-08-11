package ge.edu.freeuni.chatserver.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ge.edu.freeuni.chatserver.database.entities.MessageEntity

@Dao
interface MessageDAO {

    @Query("SELECT * FROM MessageEntity  WHERE chatId == :chatIdentifier ORDER BY date DESC LIMIT 1")
    suspend fun getLastMessageInChat(chatIdentifier: Long): MessageEntity?

    @Query("SELECT * FROM MessageEntity  WHERE (src == :user1 AND dst == :user2) OR (src == :user2 AND dst == :user1) ORDER BY date DESC LIMIT 1")
    suspend fun getLastMessageOfUsers(user1: Long, user2: Long): MessageEntity?

    @Insert
    suspend fun addMessage(message: MessageEntity)

    @Query("SELECT * FROM MessageEntity  WHERE chatId == :chatIdentifier ORDER BY date DESC ")
    suspend fun getMessages(chatIdentifier: Long): List<MessageEntity>

}