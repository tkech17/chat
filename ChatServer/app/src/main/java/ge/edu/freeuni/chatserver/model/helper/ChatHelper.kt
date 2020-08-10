package ge.edu.freeuni.chatserver.model.helper

import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.MessageDAO
import ge.edu.freeuni.chatserver.database.dao.UserDAO
import ge.edu.freeuni.chatserver.database.entities.ChatEntity
import ge.edu.freeuni.chatserver.database.entities.MessageEntity
import ge.edu.freeuni.chatserver.database.entities.UserEntity
import ge.edu.freeuni.chatserver.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ChatHelper {

    fun toDTO(from: ChatEntity): Chat {
        val userDao: UserDAO = ChatServerDatabase.getInstance(App.SELF).getUserDao()
        val messageDao: MessageDAO = ChatServerDatabase.getInstance(App.SELF).getMessageDao()

        var res: Chat? = null

        GlobalScope.launch(Dispatchers.IO) {
            val user1: UserEntity = userDao.getUserById(from.user11Id)
            val user2: UserEntity = userDao.getUserById(from.user12Id)
            val lastMessage: MessageEntity? = messageDao.getLastMessageInChat(from.id)


            res = Chat(
                id = from.id,
                user1 = UserHelper.toDTO(user1),
                user2 = UserHelper.toDTO(user2),
                lastMessage = lastMessage
            )
        }

        return res!!
    }

}