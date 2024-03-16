package com.mis.route.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.ViewMessage

open class BaseViewModel : ViewModel() {
    val viewMessage = MutableLiveData<ViewMessage>()
}
