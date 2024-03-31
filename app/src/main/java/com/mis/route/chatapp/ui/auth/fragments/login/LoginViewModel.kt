package com.mis.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepo
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepoImpl
import com.mis.route.chatapp.model.UserProvider
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel(){
    var emailLiveData = MutableLiveData<String>("")
    var passwordLiveData = MutableLiveData<String>("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    var authRepo = AuthRepoImpl()
    var events = MutableLiveData<LoginScreenEvents?>()

    fun login() {
        if(!validate()) return;
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                val user = authRepo.login(emailLiveData.value!!, passwordLiveData.value!!)
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = LoginScreenEvents.NavigateToHome
            }catch (e: Exception){
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage,
                    posButtonTitle = "OK"
                )
            }
        }
    }
    fun createAccountOnClick(){
        events.value = LoginScreenEvents.NavigateToRegister
        events.value = null
    }
    private fun validate(): Boolean {
        var isValid = true
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