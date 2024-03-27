package com.mis.route.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.model.ViewMessage

open class BaseViewModel: ViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var viewMessageLiveData = MutableLiveData<ViewMessage>()

}
