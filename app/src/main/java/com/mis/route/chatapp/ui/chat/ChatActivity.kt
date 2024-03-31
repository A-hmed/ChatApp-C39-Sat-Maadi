package com.mis.route.chatapp.ui.chat

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityChatBinding
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.ui.chat.adapter.MessageAdapter
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity

class ChatActivity : BaseActivity<ChatViewModel, ActivityChatBinding>() {
    lateinit var room: Room
    lateinit var messagesAdapter: MessageAdapter

    companion object{
        const val ROOM_KEY = "room_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        room = if(Build.VERSION.SDK_INT < TIRAMISU){
            intent.getSerializableExtra(ROOM_KEY) as Room
        }else {
            intent.getSerializableExtra(ROOM_KEY, Room::class.java)!!
        }
        viewModel.room = room
        viewModel.startListening()
        initMessagesRecyclerView()
    }

    private fun initMessagesRecyclerView() {
        messagesAdapter = MessageAdapter(listOf())
        binding.messagesRv.adapter = messagesAdapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.messages.observe(this){
            messagesAdapter.updateMessagesList(it)
        }
    }
    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun initViewModel(): ChatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

}