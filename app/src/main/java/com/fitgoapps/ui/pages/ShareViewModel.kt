package com.fitgoapps.ui.pages

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitgoapps.utils.SessionManager

class ShareViewModel(application: Application) : ViewModel() {

    var tokenParam: String = ""
    private val session = SessionManager(application)

    val isLogged : MutableState<Boolean> = mutableStateOf(false)

    fun logOut(){
        session.saveAuthToken(null)
        isLogged.value = false
    }

    fun isLogged() : Boolean{
        return session.fetchAuthToken() != null
    }

    val token = session.fetchAuthToken()

}

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShareViewModel::class.java)) {
            return ShareViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}