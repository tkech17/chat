package ge.edu.freeuni.chat.ui.messenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.user.Conversation
import ge.edu.freeuni.chat.ui.messenger.Messenger

class MessagesRecyclerViewAdapter(private val presenter: Messenger.Presenter) : RecyclerView.Adapter<MessageViewHolder>() {

    private val conversations: MutableList<Conversation> = mutableListOf()

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
        val conversation: Conversation = conversations[position]
        holder.setData(conversation)
        holder.itemView.setOnClickListener { presenter.openChatTo(conversation) }
    }

    fun setData(conversations: List<Conversation>) {
        this.conversations.clear()
        this.conversations.addAll(conversations)

        this.notifyDataSetChanged()
    }

}