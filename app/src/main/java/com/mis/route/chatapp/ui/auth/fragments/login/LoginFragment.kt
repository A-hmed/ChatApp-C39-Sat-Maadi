package com.mis.route.chatapp.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.AuthActivity
import com.mis.route.chatapp.ui.home.HomeActivity


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
        }
    }

    private fun navigateToHome() {
        if (activity == null) return
        startActivity(Intent(activity, HomeActivity::class.java))
        requireActivity().finish()
    }

    private fun navigateToRegister() {
        if (activity == null) return
        (activity as AuthActivity).navController
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}