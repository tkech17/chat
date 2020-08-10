package ge.edu.freeuni.chat.ui.messenger

import ge.edu.freeuni.chat.server.model.user.Conversation

interface Messenger {

    interface View {
        fun openChatTo(conversation: Conversation);

    }

    interface Presenter {

        fun openChatTo(conversation: Conversation)

    }

}