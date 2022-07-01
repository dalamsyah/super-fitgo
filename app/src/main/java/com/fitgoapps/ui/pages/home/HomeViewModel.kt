package com.fitgoapps.ui.pages.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fitgoapps.repository.RetrofitInstance
import com.fitgoapps.repository.RetrofitService
import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LapanganResponse
import com.fitgoapps.repository.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val TAG = "HomeViewModel"

    var result : MutableState<Any> = mutableStateOf("")
    var indicator : MutableState<Boolean> = mutableStateOf(false)

    fun fetchLapangan(token: String){

        indicator.value = true

        val retrofit = RetrofitInstance.getInstance().create(RetrofitService::class.java)
        val call = retrofit.getLapangan(token, "-6.183081", "106.826489")

        call.enqueue(object : Callback<LapanganResponse> {
            override fun onResponse(call: Call<LapanganResponse>, response: Response<LapanganResponse>) {

                indicator.value = false

                if (response.isSuccessful){
                    result.value = response.body()!!
                } else {
                    result.value = response.body()?.message ?: "Failed fetch"
                }

                Log.d(TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<LapanganResponse>, t: Throwable) {

                indicator.value = false
                result.value = "Failure"

                Log.d(TAG, "onFailure: "+t.localizedMessage)
            }

        })

    }

}