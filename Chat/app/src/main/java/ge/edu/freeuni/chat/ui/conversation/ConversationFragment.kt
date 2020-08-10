package ge.edu.freeuni.chat.ui.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.edu.freeuni.chat.server.model.user.User

class ConversationFragment : Fragment(), Conversation.View {


    private lateinit var conversation: Conversation
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: Bundle = requireArguments()

        conversation = args.get("data") as Conversation
        user = args.get("user") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}