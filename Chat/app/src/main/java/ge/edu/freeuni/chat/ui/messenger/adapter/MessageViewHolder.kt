package ge.edu.freeuni.chat.ui.messenger.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.user.Chat
import ge.edu.freeuni.chat.server.model.user.User
import ge.edu.freeuni.chat.utils.BitMapUtils
import java.text.SimpleDateFormat
import java.util.*

class MessageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var conversation: Chat
    private lateinit var currentUser: User

    private lateinit var profilePicture: ImageView
    private lateinit var person: TextView
    private lateinit var text: TextView
    private lateinit var date: TextView

    fun setData(conversation: Chat) {
        this.conversation = conversation
        updateView()
    }

    private fun updateView() {
        initFields()

        person.text = conversation.messagingTo(currentUser.username)
        text.text = conversation.lastMessage?.text
        date.text = getMessageDateText()

        val to: User = conversation.getMessagingUser(currentUser.username)

        to.picture?.let {
            profilePicture.setImageBitmap(BitMapUtils.toBitMap(it))
        }
    }

    private fun getMessageDateText(): String? {
        val messageDate: Date = conversation.lastMessage?.date ?: return null

        val now = Date()


        var minutesBeforeNow = (now.time - messageDate.time) / (1_000 * 60)

        if (minutesBeforeNow < 60) {
            return "$minutesBeforeNow min"
        }

        minutesBeforeNow /= 60
        if (minutesBeforeNow < 24) {
            return "$minutesBeforeNow hour"
        }

        return SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(messageDate)
    }


    private fun initFields() {
        profilePicture = view.findViewById(R.id.message_item_profile_picture)
        person = view.findViewById(R.id.message_item_person_name)
        text = view.findViewById(R.id.message_item_last_message)
        date = view.findViewById(R.id.message_item_last_message_date)
    }

    fun setCurrentUser(currentUser: User) {
        this.currentUser = currentUser
    }


}