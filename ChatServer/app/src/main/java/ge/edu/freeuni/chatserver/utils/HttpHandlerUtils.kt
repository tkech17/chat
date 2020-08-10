package ge.edu.freeuni.chatserver.utils

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream


fun sendResponse(httpExchange: HttpExchange, responseText: String) {
    httpExchange.sendResponseHeaders(200, responseText.length.toLong())
    val os: OutputStream = httpExchange.responseBody
    os.write(responseText.toByteArray())
    os.close()
}

fun <T : Any> exchangeToObject(exchange: HttpExchange, classType: Class<T>): T {
    val isr = InputStreamReader(exchange.requestBody, "utf-8")
    val jsonString = BufferedReader(isr).use(BufferedReader::readText)
    return Gson().fromJson(jsonString, classType)
}