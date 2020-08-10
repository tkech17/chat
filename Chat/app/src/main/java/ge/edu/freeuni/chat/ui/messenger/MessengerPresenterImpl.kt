package ge.edu.freeuni.chat.ui.messenger

import ge.edu.freeuni.chat.server.model.user.Chat

class MessengerPresenterImpl(private val view: Messenger.View) : Messenger.Presenter {

    override fun openChatTo(conversation: Chat) {
        view.openChatTo(conversation)
    }

}