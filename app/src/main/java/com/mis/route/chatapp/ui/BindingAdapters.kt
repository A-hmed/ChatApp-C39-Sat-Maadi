package com.mis.route.chatapp.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun setErrorToTextInputLayout(textInputLayout: TextInputLayout, errorText: String?){
    textInputLayout.error = errorText
}