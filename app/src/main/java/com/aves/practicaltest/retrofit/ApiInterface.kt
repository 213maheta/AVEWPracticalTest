package com.aves.practicaltest.retrofit

import com.aves.practicaltest.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos")
    suspend fun getAllUser(@Query("client_id") client_id:String): Response<List<UserModel>>

}