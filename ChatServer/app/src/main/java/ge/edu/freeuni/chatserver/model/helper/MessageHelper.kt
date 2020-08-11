package ge.edu.freeuni.chatserver.model.helper

import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.UserDAO
import ge.edu.freeuni.chatserver.database.entities.MessageEntity
import ge.edu.freeuni.chatserver.database.entities.UserEntity
import ge.edu.freeuni.chatserver.model.Message

object MessageHelper {


    suspend fun fromDTO(from: Message): MessageEntity {
        val userDao: UserDAO = ChatServerDatabase.getInstance(App.SELF).getUserDao()

        val res: MessageEntity?

        val user1: UserEntity = userDao.getUserByUsername(from.src)!!
        val user2: UserEntity = userDao.getUserByUsername(from.dst)!!

        res = MessageEntity(
            id = from.id,
            chatId = from.chatId,
            src = user1.id,
            dst = user2.id,
            text = from.text,
            date = from.date
        )

        return res
    }

    suspend fun toDTO(from: MessageEntity): Message {
        val userDao: UserDAO = ChatServerDatabase.getInstance(App.SELF).getUserDao()

        val res: Message?

        val user1: UserEntity = userDao.getUserById(from.src)
        val user2: UserEntity = userDao.getUserById(from.dst)

        res = Message(
            id = from.id,
            chatId = from.chatId,
            src = user1.username,
            dst = user2.username,
            text = from.text,
            date = from.date
        )

        return res
    }

}