package com.mis.route.chatapp.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    lateinit var room: Room
    var messageLiveData = MutableLiveData("")
    var roomsRepo = RoomsRepoImpl()
    var messages = MutableLiveData<List<RoomMessage>>()


    fun sendMessage() {
        viewModelScope.launch {
            try {
                roomsRepo.sendMessage(messageLiveData.value!!, room.id)
                messageLiveData.value = ""
            } catch (t: Throwable) {

            }
        }
    }

    fun startListening(){
        viewModelScope.launch {
            roomsRepo.startListeningToMessagesChanges(room.id).collect{
                messages.value = it
            }
        }

    }
}