package ge.edu.freeuni.chat.server.user

import ge.edu.freeuni.chat.server.model.user.LoginRequest
import ge.edu.freeuni.chat.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("ASD")
    fun get(@Body loginRequest: LoginRequest): Call<LoginRequest>

}

private var userService: UserService? = null

fun createUserService(): UserService {
    if (userService == null){
        userService = RetrofitUtils.getChatServerRetrofit().create(UserService::class.java)
    }
    return userService!!
}