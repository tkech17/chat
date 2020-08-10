package ge.edu.freeuni.chatserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.net.httpserver.HttpServer
import ge.edu.freeuni.chatserver.handlers.*
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

        httpServer.createContext(ServiceMethods.PING, PingHandler)
        httpServer.createContext(ServiceMethods.LOG_IN, LoginHandler)
        httpServer.createContext(ServiceMethods.CHAT, UserChatHandler)
        httpServer.createContext(ServiceMethods.CHAT_EXTENDED, ExtendedChatHandler)
        httpServer.createContext(ServiceMethods.ADD_CHAT, AddChatHandler)

        httpServer.start()
    }

}