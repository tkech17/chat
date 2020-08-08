package ge.edu.freeuni.chat.ui.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.message.Message
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.messenger.adapter.MessagesRecyclerViewAdapter
import java.time.LocalDateTime

class MessengerFragment : Fragment(), Messenger.View {

    private lateinit var messengerAdapter: MessagesRecyclerViewAdapter
    private lateinit var presenter: Messenger.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.messenger_fragment, container, false)

        presenter = MessengerPresenterImpl(this)

        initMessages(view)

        val user = User(
            "ABA",
            "ABA",
            "ASD",
            Message(
                "Guram",
                "Guram",
                "Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro Baro baro ",
                LocalDateTime.now()
            )

        )
        messengerAdapter.setData(
            listOf(
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user,
                user
            )
        )

        return view
    }

    private fun initMessages(view: View) {
        val messagesRecyclerView: RecyclerView = view.findViewById(R.id.messenger_fragment_messages_recycler_view)

        messengerAdapter = MessagesRecyclerViewAdapter(presenter)

        messagesRecyclerView.adapter = messengerAdapter
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}