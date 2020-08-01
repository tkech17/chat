package ge.edu.freeuni.chat.ui.connection

class ConnectionPresenterImpl(private val connectionView: Connection.View) : Connection.Presenter {

    override fun onServerConnectionSuccess() {
        connectionView.navigateToLoginFragment()
    }

    override fun onServerConnectionFail() {
        connectionView.drawFailResponseMode()
    }
}