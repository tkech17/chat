package ge.edu.freeuni.chat.ui.conversation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.chat.ChatService
import ge.edu.freeuni.chat.server.chat.createChatService
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationFragment : Fragment(), Conversation.View {


    private lateinit var chat: Chat
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: Bundle = requireArguments()

        chat = args.get("data") as Chat
        user = args.get("user") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.conversation_fragment, container, false)

        if (chat.id == 0L) {
            saveChat()
        } else {

        }

        return view
    }

    private fun saveChat() {
        GlobalScope.launch(Dispatchers.Main) {

            val chatService: ChatService = createChatService()

            chatService.addChat(chat).enqueue(object : Callback<Chat> {
                override fun onResponse(call: Call<Chat>, response: Response<Chat>) {
                    Log.i(this.javaClass.simpleName, "Successfully added chat")
                    chat = response.body()!!
                }

                override fun onFailure(call: Call<Chat>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to add chat")
                    saveChat()
                }
            })
        }
    }
}