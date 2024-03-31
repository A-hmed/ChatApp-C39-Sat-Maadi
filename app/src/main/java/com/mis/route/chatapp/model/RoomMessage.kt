package com.mis.route.chatapp.model

import com.google.firebase.Timestamp

data class RoomMessage(
    var id: String = "",
    var senderId: String = "",
    var senderName: String = "",
    var content: String = "",
    var date: Timestamp = Timestamp.now(),
){
    companion object{
        const val COLLECTION_NAME = "messagae"
    }
}