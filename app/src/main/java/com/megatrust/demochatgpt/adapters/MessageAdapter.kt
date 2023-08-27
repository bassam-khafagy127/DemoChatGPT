package com.megatrust.demochatgpt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.megatrust.demochatgpt.R
import com.megatrust.demochatgpt.data.Message

class MessageAdapter(private val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View
        return if (viewType == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false)
            UserMessageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_bot, parent, false)
            BotMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (messageList[position].sender) {
            Message.SENT_BY_BOT -> {
                (holder as BotMessageViewHolder).botMessage.text = messageList[position].message
            }

            Message.SENT_BY_ME -> {
                (holder as UserMessageViewHolder).userMessage.text = messageList[position].message
            }
        }
    }

    override fun getItemCount(): Int = messageList.size


    class UserMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMessage: TextView = itemView.findViewById(R.id.user_message_tv)

    }

    class BotMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val botMessage: TextView = itemView.findViewById(R.id.bot_message_tv)
    }


    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].sender) {
            Message.SENT_BY_ME -> 0
            Message.SENT_BY_BOT -> 1
            else -> 1
        }
    }
}