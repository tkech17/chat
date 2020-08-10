package ge.edu.freeuni.chat.ui.connection

import android.util.Log
import ge.edu.freeuni.chat.server.chat.ChatService
import ge.edu.freeuni.chat.server.chat.createChatService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConnectionPresenterImpl(private val connectionView: Connection.View) : Connection.Presenter {

    override fun checkChatServerConnection() {
        GlobalScope.launch(Dispatchers.Main) {
            checkChatServerConnectionInCoroutine()
        }
    }

    private fun checkChatServerConnectionInCoroutine() {
        connectionView.drawWaitingForResponseMode()

        val chatService: ChatService = createChatService()

        chatService.ping().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(this.javaClass.simpleName, "Successfully connected to chat server")
                onServerConnectionSuccess()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(this.javaClass.simpleName, "Failed to connect chat server")
                onServerConnectionFail()
            }
        })

    }
    private fun onServerConnectionFail() {
        connectionView.drawFailResponseMode()
    }


    private fun onServerConnectionSuccess() {
        connectionView.navigateToLoginFragment()
    }

}