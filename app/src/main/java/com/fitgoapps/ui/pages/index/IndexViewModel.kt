package com.fitgoapps.ui.pages.index

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class IndexViewModel  : ViewModel() {

    var email : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")


}