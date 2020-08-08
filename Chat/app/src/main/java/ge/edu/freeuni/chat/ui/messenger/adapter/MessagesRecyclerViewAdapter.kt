package ge.edu.freeuni.chat.ui.messenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.ui.messenger.Messenger

class MessagesRecyclerViewAdapter(private val presenter: Messenger.Presenter) : RecyclerView.Adapter<MessageViewHolder>() {

    private val users: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(view)
    }
        override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        println(position)
        val user: User = users[position]
        holder.setData(user)
    }

    fun setData(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)

        this.notifyDataSetChanged()
    }

}