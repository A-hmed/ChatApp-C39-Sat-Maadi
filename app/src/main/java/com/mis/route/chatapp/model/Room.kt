package com.mis.route.chatapp.model

import java.io.Serializable

data class Room (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var category: String = "",
): Serializable{
    companion object{
        const val COLLECTION_NAME = "rooms"
    }
}