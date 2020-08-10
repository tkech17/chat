package ge.edu.freeuni.chatserver.handlers

import android.util.Log
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.edu.freeuni.chatserver.utils.sendResponse

object PingHandler : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "GET" -> {
                Log.i(PingHandler.javaClass.simpleName, "PING")
                sendResponse(exchange, "გაგიმარჯოს")
            }
        }
    }

}