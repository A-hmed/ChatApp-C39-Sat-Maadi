package com.mis.route.chatapp.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentRegisterBinding
import com.mis.route.chatapp.ui.home.HomeActivity

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is RegisterScreenEvents.NavigateToHomeEvent ->{
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_register
    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]
}