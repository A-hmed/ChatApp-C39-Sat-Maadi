package com.mis.route.chatapp.data.repositories.rooms_repo

import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import kotlinx.coroutines.flow.Flow

interface RoomsRepo {
   suspend fun createRoom(title: String, category: String, description: String)

   suspend fun getAllRooms(): List<Room>

   suspend fun sendMessage(message: String,
                           roomId: String)

   suspend fun startListeningToMessagesChanges(roomId: String): Flow<List<RoomMessage>>
}