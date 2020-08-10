package ge.edu.freeuni.chat.server.chat

import ge.edu.freeuni.chat.server.model.user.LoginRequest
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<User>

    @GET("/ping")
    fun ping(): Call<Void>

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