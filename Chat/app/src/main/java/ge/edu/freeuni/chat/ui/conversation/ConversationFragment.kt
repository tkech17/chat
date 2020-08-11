package ge.edu.freeuni.chat.ui.conversation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.App
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.chat.ChatService
import ge.edu.freeuni.chat.server.chat.createChatService
import ge.edu.freeuni.chat.server.model.message.Message
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.conversation.adapter.ConversationRecyclerViewAdapter
import kotlinx.android.synthetic.main.conversation_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ConversationFragment : Fragment(), Conversation.View {


    private lateinit var chat: Chat
    private lateinit var user: User

    private lateinit var conversationRecyclerViewAdapter: ConversationRecyclerViewAdapter
    private lateinit var presenter: Conversation.Presenter

    private lateinit var sendMessage: ImageView
    private lateinit var newMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: Bundle = requireArguments()

        chat = args.get("data") as Chat
        user = args.get("user") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.conversation_fragment, container, false)

        presenter = ConversationPresenterImpl()

        val dstUser: User = getDstUser()

        val nickName: TextView = view.findViewById(R.id.conversation_fragment_nickname)
        nickName.text = dstUser.username

        sendMessage = view.findViewById(R.id.conversation_fragment_send_message)
        sendMessage.setOnClickListener { sendMessage() }

        newMessage = view.findViewById(R.id.conversation_fragment_edit_text)

        initAdapter(view)

        if (chat.id == 0L) {
            saveChat()
        } else {
            getAllMessages()
        }

        return view
    }


    private fun sendMessage() {
        GlobalScope.launch(Dispatchers.Main) {
            val chatService: ChatService = createChatService()

            val message = Message(
                id = 0,
                src = user.username,
                dst = getDstUser().username,
                chatId = chat.id,
                text = newMessage.text.toString(),
                date = Date()
            )

            chatService.addMessage(message).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to add message")
                    Toast.makeText(App.self, "Could Not Add Message", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i(this.javaClass.simpleName, "Successfully added message")
                    newMessage.setText("")
                    conversationRecyclerViewAdapter.addData(listOf(message))
                }
            })
        }
    }

    private fun getAllMessages() {
        GlobalScope.launch(Dispatchers.Main) {
            val chatService: ChatService = createChatService()

            chatService.getMessages(chat).enqueue(object : Callback<List<Message>> {
                override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                    Log.i(this.javaClass.simpleName, "Successfully got user messages")
                    conversationRecyclerViewAdapter.setData(response.body()!!)
                }

                override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to get user messages")
                    getAllMessages()
                }
            })
        }
    }

    private fun getDstUser(): User {
        if (user.username == chat.user1.username) {
            return chat.user2
        }
        return chat.user1
    }

    private fun initAdapter(view: View) {
        val messagesRecyclerView: RecyclerView = view.findViewById(R.id.conversation_fragment_messages_recycler_view)

        conversationRecyclerViewAdapter = ConversationRecyclerViewAdapter(presenter)

        messagesRecyclerView.adapter = conversationRecyclerViewAdapter
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
        conversationRecyclerViewAdapter.setCurrentUser(user)
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
                    Toast.makeText(App.self, "Could Not Connect Server", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_chatFragment_to_messengerFragment, bundleOf("user" to user))
                }
            })
        }
    }
}