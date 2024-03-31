package com.mis.route.chatapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.ReceivedMessageBinding
import com.mis.route.chatapp.databinding.SentMessageBinding
import com.mis.route.chatapp.model.RoomMessage
import com.mis.route.chatapp.model.UserProvider

class MessageAdapter(var messages: List<RoomMessage>): Adapter<MessageAdapter.MessageViewHolder> (){
    companion object{
        const val RECEIVED_MESSAGE_VIEW_TYPE = 0

        const val SENT_MESSAGE_VIEW_TYPE = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == SENT_MESSAGE_VIEW_TYPE){
            val binding = DataBindingUtil.inflate<SentMessageBinding>(LayoutInflater.from(parent.context),
                R.layout.sent_message, parent, false);
            MessageViewHolder(binding)
        }else {
            val binding = DataBindingUtil.inflate<ReceivedMessageBinding>(LayoutInflater.from(parent.context),
                R.layout.received_message, parent, false);
            MessageViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var message = messages[position]
        if(message.senderId == UserProvider.user!!.id!!){
            return SENT_MESSAGE_VIEW_TYPE
        }
        return RECEIVED_MESSAGE_VIEW_TYPE

    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    fun updateMessagesList(newMessages: List<RoomMessage>){
        messages = newMessages
        notifyDataSetChanged()
    }
    class MessageViewHolder(var binding: ViewDataBinding): ViewHolder(binding.root){
        fun bind(message: RoomMessage){
            if(binding is ReceivedMessageBinding){
                (binding as ReceivedMessageBinding).message = message
            }else {
                (binding as SentMessageBinding).message = message
            }
        }
    }
}