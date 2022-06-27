package com.fitgoapps.ui.pages.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fitgoapps.repository.RetrofitInstance
import com.fitgoapps.repository.RetrofitService
import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginViewModel  : ViewModel() {

    val TAG = "LoginViewModel"

    var email : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")

    fun login(){

        val retrofit = RetrofitInstance.getInstance().create(RetrofitService::class.java)
        val call = retrofit.login(LoginRequest(email = email.value, password = password.value))

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d(TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: "+t.localizedMessage)
            }

        })

    }

}