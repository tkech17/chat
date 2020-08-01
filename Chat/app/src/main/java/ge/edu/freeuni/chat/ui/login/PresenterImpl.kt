package ge.edu.freeuni.chat.ui.login

import android.content.Context

class PresenterImpl(private val view: Login.View) : Login.Presenter {

    override fun selectImage(context: Context?) {
        view.selectImage(context)
    }
}