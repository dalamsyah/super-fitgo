package com.fitgoapps.ui.pages.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel  : ViewModel() {

    var email : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")


}