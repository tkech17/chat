package ge.edu.freeuni.chat.ui.messenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.messenger.Messenger

class MessagesRecyclerViewAdapter(private val presenter: Messenger.Presenter) : RecyclerView.Adapter<MessageViewHolder>() {

    private val conversations: MutableList<Chat> = mutableListOf()
    private lateinit var currentUser: User

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(view)
    }
        override fun getItemCount(): Int {
        return conversations.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        println(position)
        val conversation: Chat = conversations[position]
        holder.setCurrentUser(currentUser)
        holder.setData(conversation)
        holder.itemView.setOnClickListener { presenter.openChatTo(conversation) }
    }

    fun setData(conversations: List<Chat>) {
        this.conversations.clear()
        this.conversations.addAll(conversations)

        this.notifyDataSetChanged()
    }

    fun setCurrentUser(user: User) {
        currentUser = user
    }

}