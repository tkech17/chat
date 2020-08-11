package ge.edu.freeuni.chat.ui.conversation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.message.Message
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.conversation.Conversation
import ge.edu.freeuni.chat.ui.messenger.Messenger

class ConversationRecyclerViewAdapter(private val presenter: Conversation.Presenter) : RecyclerView.Adapter<ConversationViewHolder>() {

    private val conversations: MutableList<Message> = mutableListOf()
    private lateinit var currentUser: User

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.single_message, parent, false)

        return ConversationViewHolder(view)
    }
        override fun getItemCount(): Int {
        return conversations.size
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        println(position)
        val conversation: Message = conversations[position]
        holder.setCurrentUser(currentUser)
        holder.setData(conversation)
    }

    fun setCurrentUser(user: User) {
        currentUser = user
    }

    fun setData(conversations: List<Message>) {
        this.conversations.clear()
        this.conversations.addAll(conversations)

        this.notifyDataSetChanged()
    }

    fun addData(messages: List<Message>) {
        this.conversations.addAll(messages)

        this.notifyDataSetChanged()
    }

}