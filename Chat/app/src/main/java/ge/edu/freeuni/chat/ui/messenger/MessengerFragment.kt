package ge.edu.freeuni.chat.ui.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.edu.freeuni.chat.R

class MessengerFragment : Fragment(), Messenger.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.messenger_fragment, container, false)


        return view
    }

}