package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.FragmentRegisterBinding

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: DB
    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel();
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        observeLiveData();
    }

    open fun observeLiveData() {
        Log.e("BaseFragment", "observeLiveData is called")
        viewModel.isLoadingLiveData.observe(this) {
            Log.e("BaseFragment", "isLoadingLiveData: $it")
            if (it) {
                showLoading();
            } else {
                hideLoading()
            }
        }
        viewModel.viewMessageLiveData.observe(this) {
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
        val builder = AlertDialog.Builder(this)
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
        val builder = AlertDialog.Builder(this)
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