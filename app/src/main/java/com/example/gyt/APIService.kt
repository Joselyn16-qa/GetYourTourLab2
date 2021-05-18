package com.example.gyt

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
    @POST
    fun register(@Url url:String, @Body user:User): Call<Response>
}