package com.fitgoapps.ui.pages.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel  : ViewModel() {

    var email : MutableState<String> = mutableStateOf("")
    var nama : MutableState<String> = mutableStateOf("")
    var nama_tim : MutableState<String> = mutableStateOf("")
    var no_hp : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")
    var conf_password : MutableState<String> = mutableStateOf("")


}