package com.fitgoapps.repository

import com.fitgoapps.repository.request.LoginRequest
import com.fitgoapps.repository.response.LapanganResponse
import com.fitgoapps.repository.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

object Constants {

    // Endpoints
    const val BASE_URL = "http://54.179.102.25/"
    const val LOGIN = "user/login"
    const val LAPANGAN = "lapangan/homepage"

}

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST(Constants.LOGIN)
    fun login(@Body loginReq: LoginRequest): Call<LoginResponse>

    @Headers("Accept: application/json")
    @GET(Constants.LAPANGAN)
    fun getLapangan(
        @Header("x-access-token") token: String,
        @Query("lat") lat: String,
        @Query("long") long: String): Call<LapanganResponse>


}