package com.megatrust.demochatgpt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.megatrust.demochatgpt.R
import com.megatrust.demochatgpt.data.Message
import com.megatrust.demochatgpt.utills.Constant

class MessageAdapter(private val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        val view: View
        return if (viewType == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false)
            UserMessageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_bot, parent, false)
            BotMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        when (messageList[position].sender) {
            Message.SENT_BY_BOT -> {
                holder.itemView.findViewById<TextView>(R.id.bot_message_tv).text = messageList[position].message
            }

            Message.SENT_BY_ME -> {
                holder.itemView.findViewById<TextView>(R.id.user_message_tv).text = messageList[position].message
            }
        }
    }

    override fun getItemCount(): Int = messageList.size


    class UserMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
        val userMessage = itemView.findViewById<TextView>(R.id.user_message_tv)

    }

    class BotMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
        val botMessage = itemView.findViewById<TextView>(R.id.bot_message_tv)
    }

    open class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].sender) {
            Message.SENT_BY_BOT -> 0
            Message.SENT_BY_ME -> 1
            else -> 1
        }
    }
}