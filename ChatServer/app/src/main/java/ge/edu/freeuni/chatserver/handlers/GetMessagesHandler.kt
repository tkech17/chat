package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.MessageDAO
import ge.edu.freeuni.chatserver.database.entities.MessageEntity
import ge.edu.freeuni.chatserver.model.Chat
import ge.edu.freeuni.chatserver.model.helper.MessageHelper
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object GetMessagesHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "GET_MESSAGES")

                GlobalScope.launch(Dispatchers.IO) {
                    val chat: Chat = exchangeToObject(exchange, Chat::class.java)
                    val messageDao: MessageDAO = ChatServerDatabase.getInstance(App.SELF).getMessageDao()

                    val messages: List<MessageEntity> = messageDao.getMessages(chat.id)
                    sendResponse(exchange, Gson().toJson(messages.map { MessageHelper.toDTO(it) }))
                }

            }
        }

    }
}