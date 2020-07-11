package ge.edu.freeuni.chatserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.io.OutputStream
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServer()
    }

    private fun startServer() {

        val httpServer: HttpServer = HttpServer.create(InetSocketAddress(ServerConfig.PORT), 0)
        httpServer.executor = Executors.newCachedThreadPool()

        httpServer.createContext("/", rootHandler)

        httpServer.start()
    }


    private val rootHandler = HttpHandler { exchange ->
        run {
            when (exchange!!.requestMethod) {
                "GET" -> {
                    sendResponse(exchange, "გაგიმარჯოს")
                }

            }
        }

    }

    private fun sendResponse(httpExchange: HttpExchange, responseText: String) {
        httpExchange.sendResponseHeaders(200, responseText.length.toLong())
        val os: OutputStream = httpExchange.responseBody
        os.write(responseText.toByteArray())
        os.close()
    }

}