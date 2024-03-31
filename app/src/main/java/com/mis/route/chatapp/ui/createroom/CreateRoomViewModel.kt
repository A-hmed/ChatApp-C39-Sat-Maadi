package com.mis.route.chatapp.ui.createroom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class CreateRoomViewModel : BaseViewModel() {
    var title = MutableLiveData("")
    var category = MutableLiveData("")
    var description = MutableLiveData("")
    var roomRepo: RoomsRepo = RoomsRepoImpl()
    var events = MutableLiveData<CreateRoomEvents?>()

    fun createRoom() {
        Log.e("createRoom", "createRoom")
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                roomRepo.createRoom(title.value!!, category.value!!, description.value!!)
                isLoadingLiveData.value = false
                events.value = CreateRoomEvents.RoomCreated
            } catch (error: Throwable) {
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = error.localizedMessage, posButtonTitle = "ok"
                )
            }
        }
    }
}