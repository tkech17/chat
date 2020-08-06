package ge.edu.freeuni.chat.ui.login

import android.content.Context
import android.util.Log
import ge.edu.freeuni.chat.server.chat.ChatService
import ge.edu.freeuni.chat.server.chat.createChatService
import ge.edu.freeuni.chat.server.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterImpl(private val view: Login.View) : Login.Presenter {

    override fun selectImage(context: Context?) {
        view.selectImage(context)
    }

    override fun startChat(user: User) {
        view.drawWaitingForResponseMode()

        GlobalScope.launch(Dispatchers.Main) {

            val chatService: ChatService = createChatService()

            chatService.get().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i(this.javaClass.simpleName, "Successfully retrieved user from chat server")
                    onServerConnectionSuccess(response)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to connect chat server")
                    onServerConnectionFail()
                    onServerConnectionSuccess(Response.success("asd"))
                }
            })

        }

    }


    private fun onServerConnectionSuccess(response: Response<String>) {
        if (response.isSuccessful) {
            view.navigateToChatFragment()
        } else {
            TODO()
        }
    }

    private fun onServerConnectionFail() {
        view.drawFailResponseMode()
    }


}