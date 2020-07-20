package com.example.keyboard.api

import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.data.GetStatus
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DBService {
    @GET("/item/read")
    fun getItem(
        @Body item: List<KeyItem>
    ): Call<List<KeyItem>>

    @POST("/item/create")
    fun itemCreate(
        @Body item: KeyItem
    ): Call<GetStatus>

    @POST("/user/login/{id}/{pw}")
    fun signIn(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetStatus>

    @POST("/user/signUp/{id}/{pw}")
    fun signUp(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetStatus>


}