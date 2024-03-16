package com.mis.route.chatapp.data.repositories.auth_repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl(): AuthRepo {
     override suspend fun register(userName: String, email: String, password: String): MyUser {
        var authResult = Firebase.auth.
            createUserWithEmailAndPassword(email ,password).await()
        return MyUser(id = authResult.user!!.uid, email = email, userName = userName)
    }

    override fun login(email: String, password: String): MyUser {
        throw Exception("\"not implemented yet\"")
    }
}