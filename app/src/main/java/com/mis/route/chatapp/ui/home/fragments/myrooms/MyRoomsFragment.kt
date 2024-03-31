package com.mis.route.chatapp.ui.home.fragments.myrooms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentMyRoomsBinding
import com.mis.route.chatapp.ui.chat.ChatActivity
import com.mis.route.chatapp.ui.home.fragments.myrooms.adapter.RoomsAdapter

class MyRoomsFragment : BaseFragment<MyRoomsViewModel, FragmentMyRoomsBinding>() {
    lateinit var adapter: RoomsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRoomsRecyclerView()
    }

    private fun initRoomsRecyclerView() {
        adapter = RoomsAdapter(listOf()) { position, room ->
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(ChatActivity.ROOM_KEY, room)
            startActivity(intent)
        }
        binding.roomsRecycler.adapter = adapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.rooms.observe(this) {
            adapter.updateRooms(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshRooms()
    }

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms

    override fun initViewModel(): MyRoomsViewModel =
        ViewModelProvider(this)[MyRoomsViewModel::class.java]

}