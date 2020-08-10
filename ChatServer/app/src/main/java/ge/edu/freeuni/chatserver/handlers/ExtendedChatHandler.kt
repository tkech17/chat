package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.MessageDAO
import ge.edu.freeuni.chatserver.database.dao.UserDAO
import ge.edu.freeuni.chatserver.database.entities.UserEntity
import ge.edu.freeuni.chatserver.model.Chat
import ge.edu.freeuni.chatserver.model.ChatRequest
import ge.edu.freeuni.chatserver.model.User
import ge.edu.freeuni.chatserver.model.helper.UserHelper
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ExtendedChatHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {

        when (exchange.requestMethod) {

            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "CHAT")

                GlobalScope.launch(Dispatchers.IO) {
                    val chatRequest: ChatRequest = exchangeToObject(exchange, ChatRequest::class.java)
                    val userDao: UserDAO = ChatServerDatabase.getInstance(App.SELF).getUserDao()
                    val messageDao: MessageDAO = ChatServerDatabase.getInstance(App.SELF).getMessageDao()

                    val currentUser: User = UserHelper.toDTO(userDao.getUserByUsername(chatRequest.currentUsername)!!)
                    val users: List<UserEntity> = userDao.getUsersByUsername(chatRequest.dstUsername, chatRequest.currentUsername)


                    val chats: List<Chat> = users.map { UserHelper.toDTO(it) }
                        .map {
                            Chat(
                                id = 0,
                                user1 = currentUser,
                                user2 = it,
                                lastMessage = messageDao.getLastMessageOfUsers(currentUser.id, it.id)
                            )
                        }

                    sendResponse(exchange, Gson().toJson(chats))
                }
            }

        }

    }

}