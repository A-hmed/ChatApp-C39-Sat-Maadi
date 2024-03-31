package com.mis.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.mis.route.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class MyRoomsViewModel: BaseViewModel() {
    var rooms = MutableLiveData<List<Room>>()
    var repo = RoomsRepoImpl()

    fun refreshRooms(){
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                rooms.value = repo.getAllRooms();
                isLoadingLiveData.value = false
            }catch (t: Throwable){
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = t.localizedMessage, posButtonTitle = "ok"
                )
            }
        }
    }
}