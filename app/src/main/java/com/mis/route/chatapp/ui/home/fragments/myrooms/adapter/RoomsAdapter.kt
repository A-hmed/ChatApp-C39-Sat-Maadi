package com.mis.route.chatapp.ui.home.fragments.myrooms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.ItemRoomBinding
import com.mis.route.chatapp.model.Room

class RoomsAdapter(var rooms: List<Room>, var onItemClick: (Int, Room) -> Unit): Adapter<RoomsAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
       val binding = DataBindingUtil.inflate<ItemRoomBinding>(
           LayoutInflater.from(parent.context), R.layout.item_room, parent,
           false)
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(position, rooms[position])
    }

    fun updateRooms(newRooms: List<Room>){
        rooms = newRooms
        notifyDataSetChanged()
    }

    inner class RoomViewHolder(var binding: ItemRoomBinding): ViewHolder(binding.root){
        fun bind(postition: Int, room: Room){
            binding.room = room
            binding.root.setOnClickListener {
                onItemClick.invoke(postition, room);
            }
            binding.executePendingBindings()
        }
    }
}