package ge.edu.freeuni.chat.ui.messenger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.chat.ChatService
import ge.edu.freeuni.chat.server.chat.createChatService
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.ChatRequest
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.messenger.adapter.MessagesRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessengerFragment : Fragment(), Messenger.View {

    private lateinit var user: User
    private lateinit var messengerAdapter: MessagesRecyclerViewAdapter
    private lateinit var presenter: Messenger.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var tryRetrieveChat: MaterialButton
    private lateinit var searchVIew: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = requireArguments().get("user") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.messenger_fragment, container, false)

        presenter = MessengerPresenterImpl(this)

        progressBar = view.findViewById(R.id.messenger_fragment_indeterminate_progress_bar)

        tryRetrieveChat = view.findViewById(R.id.messenger_fragment_try_retrieve_chat)
        tryRetrieveChat.setOnClickListener { retrieveChat() }

        searchVIew = view.findViewById(R.id.messenger_fragment_search_view)
        searchVIew.setOnQueryTextListener(onQueryTextListener)


        initMessages(view)

        retrieveChat()

        return view
    }

    private val onQueryTextListener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String): Boolean {
            return onText(query);
        }

        override fun onQueryTextChange(newText: String): Boolean {
            return onText(newText)
        }

        private fun onText(text: String): Boolean {
            if (text.length > 3) {
                retrieveExtendedChat()
                return true
            }
            return false
        }
    }

    private fun retrieveChat() {
        GlobalScope.launch(Dispatchers.Main) {

            tryRetrieveChat.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            val chatService: ChatService = createChatService()

            chatService.getUserChat(user.id).enqueue(object : Callback<List<Chat>> {
                override fun onResponse(call: Call<List<Chat>>, response: Response<List<Chat>>) {
                    Log.i(this.javaClass.simpleName, "Successfully got user chat")
                    messengerAdapter.setCurrentUser(user)
                    messengerAdapter.setData(response.body()!!)
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onFailure(call: Call<List<Chat>>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to get user chat")
                    progressBar.visibility = View.INVISIBLE
                    tryRetrieveChat.visibility = View.VISIBLE
                }
            })

        }
    }

    private fun retrieveExtendedChat() {
        GlobalScope.launch(Dispatchers.Main) {

            tryRetrieveChat.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            val chatService: ChatService = createChatService()

            val chatRequest = ChatRequest(user.username, searchVIew.query.toString())

            chatService.getUserChatExtended(chatRequest).enqueue(object : Callback<List<Chat>> {
                override fun onResponse(call: Call<List<Chat>>, response: Response<List<Chat>>) {
                    Log.i(this.javaClass.simpleName, "Successfully got user chat")
                    messengerAdapter.setCurrentUser(user)
                    messengerAdapter.setData(response.body()!!)
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onFailure(call: Call<List<Chat>>, t: Throwable) {
                    Log.e(this.javaClass.simpleName, "Failed to get user chat")
                    progressBar.visibility = View.INVISIBLE
                    tryRetrieveChat.visibility = View.VISIBLE
                }
            })

        }
    }

    private fun initMessages(view: View) {
        val messagesRecyclerView: RecyclerView = view.findViewById(R.id.messenger_fragment_messages_recycler_view)

        messengerAdapter = MessagesRecyclerViewAdapter(presenter)

        messagesRecyclerView.adapter = messengerAdapter
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun openChatTo(chat: Chat) {
        findNavController().navigate(R.id.action_messengerFragment_to_chatFragment, bundleOf("data" to chat, "user" to user))
    }

}