package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.App
import ge.edu.freeuni.chatserver.database.ChatServerDatabase
import ge.edu.freeuni.chatserver.database.daos.UserDAO
import ge.edu.freeuni.chatserver.database.entities.UserEntity
import ge.edu.freeuni.chatserver.model.LoginRequest
import ge.edu.freeuni.chatserver.utils.exchangeToObject
import ge.edu.freeuni.chatserver.utils.sendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object LoginHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                Log.i(PingHandler.javaClass.simpleName, "PING")

                GlobalScope.launch(Dispatchers.IO) {
                    val loginRequest: LoginRequest = exchangeToObject(exchange, LoginRequest::class.java)
                    val userDao: UserDAO = ChatServerDatabase.getInstance(App.SELF).getUserDao()
                    var user: UserEntity? = userDao.getUserByUsername(loginRequest.username)

                    if (user == null) {
                        user = userDao.save(
                            UserEntity(
                                username = loginRequest.username,
                                whatIDo = loginRequest.whatIDo,
                                picture = loginRequest.imageBase64
                            )
                        )
                    }

                    sendResponse(exchange, Gson().toJson(user))
                }

            }
        }

    }
}