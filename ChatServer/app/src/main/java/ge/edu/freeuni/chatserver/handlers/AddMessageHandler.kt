package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.dao.MessageDAO
import ge.edu.freeuni.chatserver.database.entities.MessageEntity
import ge.edu.freeuni.chatserver.model.Message
import ge.edu.freeuni.chatserver.model.helper.MessageHelper
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object AddMessageHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {

        when (exchange.requestMethod) {
            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "ADD_MESSAGE")

                GlobalScope.launch(Dispatchers.IO) {
                    val message: Message = exchangeToObject(exchange, Message::class.java)
                    val messageDao: MessageDAO = ChatServerDatabase.getInstance(App.SELF).getMessageDao()

                    val res: MessageEntity = MessageHelper.fromDTO(message)

                    messageDao.addMessage(res)
                    sendResponse(exchange, "Done")
                }

            }
        }
    }
}