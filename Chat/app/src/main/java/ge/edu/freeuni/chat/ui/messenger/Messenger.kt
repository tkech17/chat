package ge.edu.freeuni.chat.ui.messenger

import ge.edu.freeuni.chat.server.model.user.Chat

interface Messenger {

    interface View {
        fun openChatTo(conversation: Chat);

    }

    interface Presenter {

        fun openChatTo(conversation: Chat)

    }

}