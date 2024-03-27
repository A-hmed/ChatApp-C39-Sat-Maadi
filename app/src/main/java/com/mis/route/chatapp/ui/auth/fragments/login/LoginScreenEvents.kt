package com.mis.route.chatapp.ui.auth.fragments.login

sealed class LoginScreenEvents {
    data object NavigateToHome: LoginScreenEvents()

    data object NavigateToRegister: LoginScreenEvents()
}