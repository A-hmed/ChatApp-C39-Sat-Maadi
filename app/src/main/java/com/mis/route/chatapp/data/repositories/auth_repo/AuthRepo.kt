package com.mis.route.chatapp.data.repositories.auth_repo

import com.mis.route.chatapp.model.MyUser

interface AuthRepo {
    suspend fun register(userName: String, email: String, password: String): MyUser

    suspend fun login(email: String, password: String): MyUser
}