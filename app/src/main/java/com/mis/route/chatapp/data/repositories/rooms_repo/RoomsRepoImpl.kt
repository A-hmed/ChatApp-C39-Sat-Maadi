package com.mis.route.chatapp.data.repositories.rooms_repo

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import com.mis.route.chatapp.model.UserProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RoomsRepoImpl : RoomsRepo {
    override suspend fun createRoom(title: String, category: String, description: String) {
        val docRef = FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME)
            .document()
        val room = Room(
            id = docRef.id, title = title,
            category = category,
            description = description
        )
        Log.e("createRoom", "room = ${room}")
        docRef.set(room).await()
    }

    override suspend fun getAllRooms(): List<Room> {
        val collection = FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME)
        val querySnapshot = collection.get().await()
        return querySnapshot.toObjects(Room::class.java)
    }

    override suspend fun sendMessage(
        message: String,
        roomId: String
    ) {
        val roomDoc = FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME)
            .document(roomId)
        val messageDocRef =
            roomDoc.collection(RoomMessage.COLLECTION_NAME).document()
        val roomMessage = RoomMessage(
            id = messageDocRef.id,
            senderId = UserProvider.user!!.id!!,
            senderName = UserProvider.user!!.userName!!,
            content = message,
            date = Timestamp.now(),
        )
        messageDocRef.set(roomMessage).await()
    }

    override suspend fun startListeningToMessagesChanges(roomId: String):
            Flow<List<RoomMessage>> = flow {
        FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME).document(roomId)
            .collection(RoomMessage.COLLECTION_NAME).orderBy("date").snapshots().collect {
                emit(it.toObjects(RoomMessage::class.java))
            }
    }


}