package com.mis.route.chatapp.ui.createroom


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityRoomCreationBinding


class RoomCreationActivity : BaseActivity<CreateRoomViewModel, ActivityRoomCreationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.events.observe(this){
            it?.let {
                when(it){
                    CreateRoomEvents.RoomCreated -> {
                        finish()
                    }
                }
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_room_creation
    override fun initViewModel(): CreateRoomViewModel =
        ViewModelProvider(this)[CreateRoomViewModel::class.java]
}