package ge.edu.freeuni.chat.server.chat

import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.ChatRequest
import ge.edu.freeuni.chat.server.model.user.LoginRequest
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<User>

    @GET("/ping")
    fun ping(): Call<Void>

    @POST("/chat")
    fun getUserChat(@Body userId: Long): Call<List<Chat>>

    @POST("/chat-extended")
    fun getUserChatExtended(@Body chatRequest: ChatRequest): Call<List<Chat>>

}

private var chatService: ChatService? = null

fun createChatService(): ChatService {
    if (chatService == null){
        chatService = RetrofitUtils
            .getChatServerRetrofit()
            .create(ChatService::class.java)
    }
    return chatService!!
}