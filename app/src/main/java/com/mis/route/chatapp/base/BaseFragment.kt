package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.FragmentRegisterBinding

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    lateinit var viewModel: VM
    lateinit var binding: DB
    var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel();
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        observeLiveData();
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    open fun observeLiveData() {
        Log.e("BaseFragment", "observeLiveData is called")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            Log.e("BaseFragment", "isLoadingLiveData: $it")
            if (it) {
                showLoading();
            } else {
                hideLoading()
            }
        }
        viewModel.viewMessageLiveData.observe(viewLifecycleOwner) {
            showDialog(
                it.title,
                it.message,
                it.posButtonTitle,
                it.negButtonTitle,
                it.onPosButtonClick,
                it.onNegButtonClick
            )
        }
    }
    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM

    fun showLoading() {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_loading)
        dialog = builder.create();
        dialog?.show()

    }

    fun hideLoading() {
        dialog?.dismiss()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        posButtonTitle: String? = null,
        negButtonTitle: String? = null,
        onPosButtonClick: (() -> Unit)? = null,
        onNegButtonClick: (() -> Unit)? = null,
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        posButtonTitle.let {
            builder.setPositiveButton(
                posButtonTitle
            ) { dialog, p1 ->
                dialog.dismiss();
                onPosButtonClick?.invoke()
            };
        }
        negButtonTitle.let {
            builder.setPositiveButton(
                negButtonTitle
            ) { dialog, p1 ->
                dialog.dismiss();
                onNegButtonClick?.invoke()
            };
        }
        builder.create().show();
    }
}