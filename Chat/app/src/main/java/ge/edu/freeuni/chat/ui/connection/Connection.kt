package ge.edu.freeuni.chat.ui.connection

interface Connection {

    interface View {

        fun drawFailResponseMode()
        fun navigateToLoginFragment();

    }

    interface Presenter {

        fun onServerConnectionSuccess()
        fun onServerConnectionFail()

    }

}