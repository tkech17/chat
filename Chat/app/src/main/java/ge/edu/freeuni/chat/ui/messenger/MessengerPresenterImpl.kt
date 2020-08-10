package ge.edu.freeuni.chat.ui.messenger

import ge.edu.freeuni.chat.server.model.user.Conversation

class MessengerPresenterImpl(private val view: Messenger.View) : Messenger.Presenter {

    override fun openChatTo(conversation: Conversation) {
        view.openChatTo(conversation)
    }

}