package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepo
import com.mis.route.chatapp.data.repositories.auth_repo.AuthRepoImpl
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    var authRepo: AuthRepo = AuthRepoImpl()
    var emailLiveData = MutableLiveData<String>("")
    var userNameLiveData = MutableLiveData<String>("")
    var passwordLiveData = MutableLiveData<String>("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var userNameErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()



    fun register(){
        if(!validate()) return;
        viewModelScope.launch {
            authRepo.register(userNameLiveData.value!!, emailLiveData.value!!,
                passwordLiveData.value!!)
        }

    }

    fun validate(): Boolean{
        var isValid = true
        if(userNameLiveData.value.isNullOrEmpty()){
            userNameErrorLiveData.value = "Please enter valid user name";
            isValid = false
        }else {
            userNameErrorLiveData.value = null
        }
        if(emailLiveData.value.isNullOrEmpty()){
            emailErrorLiveData.value = "Please enter valid email";
            isValid = false
        }else {
            emailErrorLiveData.value = null
        }
        if(passwordLiveData.value.isNullOrEmpty()){
            passwordErrorLiveData.value = "Please enter valid password";
            isValid = false
        }else {
            passwordErrorLiveData.value = null
        }
        return isValid
    }
}