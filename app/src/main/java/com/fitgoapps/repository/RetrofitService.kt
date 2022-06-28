package com.fitgoapps.repository

import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

object Constants {

    // Endpoints
    const val BASE_URL = "http://54.179.102.25/"
    const val LOGIN = "user/login"

}

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST(Constants.LOGIN)
    fun login(@Body loginReq: LoginRequest): Call<LoginResponse>


}