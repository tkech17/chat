package ge.edu.freeuni.chat.ui.login

import android.content.Context
import ge.edu.freeuni.chat.server.model.user.LoginRequest

interface Login {

    interface View {
        fun selectImage(context: Context?)
        fun drawFailResponseMode()
        fun drawWaitingForResponseMode()
        fun navigateToChatFragment()
    }

    interface Presenter {
        fun selectImage(context: Context?)
        fun startChat(loginRequest: LoginRequest)
    }

}