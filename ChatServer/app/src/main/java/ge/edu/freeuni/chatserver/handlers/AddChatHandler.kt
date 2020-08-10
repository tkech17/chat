package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.ChatDAO
import ge.edu.freeuni.chatserver.model.Chat
import ge.edu.freeuni.chatserver.model.helper.ChatHelper
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object AddChatHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "LOGIN")

                GlobalScope.launch(Dispatchers.IO) {
                    val chat: Chat = exchangeToObject(exchange, Chat::class.java)
                    val chatDao: ChatDAO = ChatServerDatabase.getInstance(App.SELF).getChatDao()
                    chatDao.save(ChatHelper.fromDTO(chat))

                    val result:Chat = ChatHelper.toDTO(chatDao.getUsersChat(chat.user1.id, chat.user2.id))
                    sendResponse(exchange, Gson().toJson(result))
                }

            }
        }

    }
}