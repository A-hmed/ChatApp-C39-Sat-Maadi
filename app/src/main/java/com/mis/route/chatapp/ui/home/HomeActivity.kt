package com.mis.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.ActivityHomeBinding
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity
import com.mis.route.chatapp.ui.home.adapter.RoomsViewPagerAdapter
import com.mis.route.chatapp.ui.roomdetails.RoomDetailsActivity

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding: ActivityHomeBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRoomsViewPager()
        binding.addRoomBtn.setOnClickListener { navigateToCreateRoom() }
    }

    private fun navigateToCreateRoom() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
    }

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles = resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}