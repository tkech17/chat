package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.ChatDAO
import ge.edu.freeuni.chatserver.database.entities.ChatEntity
import ge.edu.freeuni.chatserver.model.Chat
import ge.edu.freeuni.chatserver.model.helper.ChatHelper
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object UserChatHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {

        when (exchange.requestMethod) {

            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "CHAT")

                GlobalScope.launch(Dispatchers.IO) {
                    val userId: Long = exchangeToObject(exchange, Long::class.java)
                    val chatDao: ChatDAO = ChatServerDatabase.getInstance(App.SELF).getChatDao()

                    val userChat: List<ChatEntity> = chatDao.getUserChat(userId)
                    val result: List<Chat> = userChat.map { ChatHelper.toDTO(it) }

                    sendResponse(exchange, Gson().toJson(result))
                }
            }

        }

    }

}