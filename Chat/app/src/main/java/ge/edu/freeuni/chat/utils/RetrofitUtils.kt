package ge.edu.freeuni.chat.utils

import ge.edu.freeuni.chat.server.ChatServerParams
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtils {

    private var chatServerRetrofit: Retrofit? = null


    fun getChatServerRetrofit(): Retrofit {
        if (chatServerRetrofit == null) {

            chatServerRetrofit = Retrofit.Builder()
                .baseUrl(ChatServerParams.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        return chatServerRetrofit!!
    }

}
