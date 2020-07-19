package com.example.keyboard.api

import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.data.GetUserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DBService {
    @GET("/item/read")
    fun getItem(
    ): Call<List<KeyItem>>

    @POST("/user/login/{id}/{pw}")
    fun signIn(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetUserData>

    @POST("/user/signUp/{id}/{pw}")
    fun signUp(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetUserData>
}