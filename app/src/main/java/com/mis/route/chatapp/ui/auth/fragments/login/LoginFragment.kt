package com.mis.route.chatapp.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.AuthActivity
import com.mis.route.chatapp.ui.home.HomeActivity


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun observeLiveData() {
        Log.e("LoginFragment", "events")
        super.observeLiveData()
        viewModel.events.observe(viewLifecycleOwner){
            it?.let { event->
                when(event){
                    is LoginScreenEvents.NavigateToHome -> {
                        val intent = Intent(activity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    is LoginScreenEvents.NavigateToRegister -> {
                        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                    }
                }
            }

        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_login
    override fun initViewModel(): LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
}