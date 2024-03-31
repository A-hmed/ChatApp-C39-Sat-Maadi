package com.mis.route.chatapp.ui.createroom

sealed class CreateRoomEvents {
    data object RoomCreated: CreateRoomEvents()
}