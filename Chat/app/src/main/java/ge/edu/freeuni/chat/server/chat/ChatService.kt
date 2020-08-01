package ge.edu.freeuni.chat.server.chat

import ge.edu.freeuni.chat.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.http.GET

interface ChatService {

    @GET("ASD")
    fun get(): Call<String>

}

private var chatService: ChatService? = null

fun createChatService(): ChatService {
    if (chatService == null){
        chatService = RetrofitUtils.getChatServerRetrofit().create(ChatService::class.java)
    }
    return chatService!!
}