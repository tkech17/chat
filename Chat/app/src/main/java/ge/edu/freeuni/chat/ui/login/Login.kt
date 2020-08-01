package ge.edu.freeuni.chat.ui.login

import android.content.Context

interface Login {

    interface View {
        fun selectImage(context: Context?)
    }

    interface Presenter {
        fun selectImage(context: Context?)
    }

}