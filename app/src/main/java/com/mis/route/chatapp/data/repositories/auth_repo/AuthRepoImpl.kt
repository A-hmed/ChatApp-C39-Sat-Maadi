package com.mis.route.chatapp.data.repositories.auth_repo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
     override suspend fun register(userName: String, email: String, password: String): MyUser {
        val authResult = Firebase.auth.
            createUserWithEmailAndPassword(email ,password).await()
         val user = MyUser(id = authResult.user!!.uid, email = email, userName = userName)
         Firebase.firestore.collection(MyUser.COLLECTION_NAME).document(user.id!!)
             .set(user)
        return user;
    }

    override suspend fun login(email: String, password: String): MyUser {
      val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val docRef =
            Firebase.firestore.collection(MyUser.COLLECTION_NAME).document(authResult.user!!.uid)
        val snapshot = docRef.get().await()
        return snapshot.toObject(MyUser::class.java)!!
    }
}