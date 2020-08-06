package ge.edu.freeuni.chat.ui.connection

interface Connection {

    interface View {

        fun drawFailResponseMode()
        fun navigateToLoginFragment()
        fun drawWaitingForResponseMode()
    }

    interface Presenter {

        fun checkChatServerConnection()

    }

}
