package ge.edu.freeuni.chat.utils

import ge.edu.freeuni.chat.server.ChatServerParams
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitUtils {

    private var chatServerRetrofit: Retrofit? = null


    fun getChatServerRetrofit(): Retrofit {
        if (chatServerRetrofit == null) {

            val okHttpClient: OkHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .callTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()

            chatServerRetrofit = Retrofit.Builder()
                .baseUrl(ChatServerParams.URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        return chatServerRetrofit!!
    }

}
