package ge.edu.freeuni.chat.ui.connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import ge.edu.freeuni.chat.R

class ConnectionFragment : Fragment(), Connection.View {

    private lateinit var connectionInfo: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var connectButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.connection_fragment, container, false)

        val connectionPresenter = ConnectionPresenterImpl(this)

        connectionInfo = view.findViewById(R.id.connection_fragment_checking_connection)
        progressBar = view.findViewById(R.id.connection_fragment_indeterminate_progress_bar)
        connectButton = view.findViewById(R.id.connection_fragment_connect_button)
        connectButton.setOnClickListener { connectionPresenter.checkChatServerConnection() }

        connectionPresenter.checkChatServerConnection()

        return view
    }


    override fun drawWaitingForResponseMode() {
        connectButton.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        connectionInfo.text = resources.getString(R.string.checking_connection)
    }

    override fun drawFailResponseMode() {
        progressBar.visibility = View.INVISIBLE
        connectButton.visibility = View.VISIBLE
        connectionInfo.text = resources.getString(R.string.connection_failed)
    }

    override fun navigateToLoginFragment() {
        findNavController()
            .navigate(R.id.action_connectionFragment_to_loginFragment)
    }


}
