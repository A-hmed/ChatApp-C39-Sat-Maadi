package com.mis.route.chatapp.ui.auth.fragments.login

import com.mis.route.chatapp.database.User
sealed class LoginViewEvent {
    object NavigateToRegister : LoginViewEvent()
    data class NavigateToHome(val user: User) : LoginViewEvent()
}
