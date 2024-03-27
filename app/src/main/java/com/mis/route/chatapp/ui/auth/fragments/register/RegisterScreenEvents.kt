package com.mis.route.chatapp.ui.auth.fragments.register


sealed class RegisterScreenEvents{
    data object NavigateToHomeEvent: RegisterScreenEvents()
}