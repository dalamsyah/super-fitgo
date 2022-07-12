package com.fitgoapps.ui.pages.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fitgoapps.repository.RetrofitInstance
import com.fitgoapps.repository.RetrofitService
import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LoginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginViewModel  : ViewModel() {

    val TAG = "LoginViewModel"

    var email : MutableState<String> = mutableStateOf("")
    var password : MutableState<String> = mutableStateOf("")

    var loginObserver : MutableState<Any> = mutableStateOf("")
    var indicator : MutableState<Boolean> = mutableStateOf(false)

    fun login(){

        indicator.value = true

        val retrofit = RetrofitInstance.getInstance().create(RetrofitService::class.java)
        val call = retrofit.login(LoginRequest(email = "iwan@gmail.com", password = "keb@ntenan2018"))

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                indicator.value = false

                if (response.isSuccessful){
                    loginObserver.value = response
                } else {

                    val gson = Gson()
                    val type = object : TypeToken<LoginResponse>() {}.type
                    var errorResponse: LoginResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)

                    loginObserver.value = errorResponse?.message ?: "Failed login"
                }

                Log.d(TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                indicator.value = false
                loginObserver.value = "Failure"

                Log.d(TAG, "onFailure: "+t.localizedMessage)
            }

        })

    }

}