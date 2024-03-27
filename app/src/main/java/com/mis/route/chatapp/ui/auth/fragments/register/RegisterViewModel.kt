package com.mis.route.chatapp.ui.auth.fragments.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepo
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepoImpl
import com.mis.route.chatapp.model.UserProvider
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    var authRepo: AuthRepo = AuthRepoImpl()
    var emailLiveData = MutableLiveData<String>("")
    var userNameLiveData = MutableLiveData<String>("")
    var passwordLiveData = MutableLiveData<String>("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var userNameErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    var events = MutableLiveData<RegisterScreenEvents>()


    fun register() {
        if (!validate()) return
        viewModelScope.launch {
            isLoadingLiveData.value = true
            Log.e("RegisterViewModel", "register isLoadingLiveData ${isLoadingLiveData.value}")
            try {
                val user = authRepo.register(
                    userNameLiveData.value!!, emailLiveData.value!!,
                    passwordLiveData.value!!
                )
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = RegisterScreenEvents.NavigateToHomeEvent

            } catch (e: Exception) {
                print("E = $e")
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage,
                    posButtonTitle = "OK"
                )
            }

        }

    }

    fun validate(): Boolean {
        var isValid = true
        if (userNameLiveData.value.isNullOrEmpty()) {
            userNameErrorLiveData.value = "Please enter valid user name"
            isValid = false
        } else {
            userNameErrorLiveData.value = null
        }
        if (emailLiveData.value.isNullOrEmpty()) {
            emailErrorLiveData.value = "Please enter valid email"
            isValid = false
        } else {
            emailErrorLiveData.value = null
        }
        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordErrorLiveData.value = "Please enter valid password"
            isValid = false
        } else {
            passwordErrorLiveData.value = null
        }
        return isValid
    }
}