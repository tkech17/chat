package ge.edu.freeuni.chat.ui.login

import android.content.Context
import ge.edu.freeuni.chat.server.model.user.LoginRequest
import ge.edu.freeuni.chat.server.model.user.User

interface Login {

    interface View {
        fun selectImage(context: Context?)
        fun drawFailResponseMode()
        fun drawWaitingForResponseMode()
        fun navigateToChatFragment(user: User)
    }

    interface Presenter {
        fun selectImage(context: Context?)
        fun startChat(loginRequest: LoginRequest)
    }

}