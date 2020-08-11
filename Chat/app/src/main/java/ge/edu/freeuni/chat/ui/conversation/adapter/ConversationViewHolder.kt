package ge.edu.freeuni.chat.ui.conversation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.message.Message
import ge.edu.freeuni.chat.server.model.user.User
import java.text.SimpleDateFormat
import java.util.*

class ConversationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var conversation: Message
    private lateinit var currentUser: User

    private lateinit var text: TextView
    private lateinit var date: TextView

    fun setData(conversation: Message) {
        this.conversation = conversation
        updateView()
    }

    private fun updateView() {
        initFields()

        text.text = conversation.text
        date.text = getMessageDateText()
    }

    private fun getMessageDateText(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(conversation.date)
    }


    private fun initFields() {
        if (conversation.src != currentUser.username) {
            text = view.findViewById(R.id.single_message_message_user_message)
            date = view.findViewById(R.id.single_message_date_user_message)
        } else {
            text = view.findViewById(R.id.single_message_message)
            date = view.findViewById(R.id.single_message_date)
        }

        text.visibility = View.VISIBLE
        date.visibility = View.VISIBLE

    }

    fun setCurrentUser(currentUser: User) {
        this.currentUser = currentUser
    }


}