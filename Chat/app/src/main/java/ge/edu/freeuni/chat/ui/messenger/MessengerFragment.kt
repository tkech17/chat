package ge.edu.freeuni.chat.ui.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.message.Message
import ge.edu.freeuni.chat.server.model.user.Conversation
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.messenger.adapter.MessagesRecyclerViewAdapter
import java.time.LocalDateTime

class MessengerFragment : Fragment(), Messenger.View {

    private lateinit var user: User
    private lateinit var messengerAdapter: MessagesRecyclerViewAdapter
    private lateinit var presenter: Messenger.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = requireArguments().get("user") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.messenger_fragment, container, false)

        presenter = MessengerPresenterImpl(this)

        initMessages(view)

        val conversation = Conversation(
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
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation,
                conversation
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

    override fun openChatTo(conversation: Conversation) {
        findNavController().navigate(R.id.action_messengerFragment_to_chatFragment, bundleOf("data" to conversation, "user" to user))
    }

}