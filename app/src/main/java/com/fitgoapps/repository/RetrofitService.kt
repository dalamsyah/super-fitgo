package com.fitgoapps.repository

import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST("user/login")
    fun login(@Body loginReq: LoginRequest): Call<LoginResponse>

//    @GET
//    fun getUserFavorites()

}