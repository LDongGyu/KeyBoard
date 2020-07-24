package com.example.keyboard.api

import com.example.keyboard.data.GetCategory
import com.example.keyboard.data.GetID
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.CategoryList.CategoryListItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DBService {
    @GET("/item/read/{id}")
    fun getItem(
        @Path("id") id: Int
    ): Call<List<KeyItem>>

    @POST("/item/create")
    fun itemCreate(
        @Body item: KeyItem
    ): Call<GetStatus>

    @GET("/category/read/{id}")
    fun getCategory(
        @Path("id") id: Int
    ): Call<List<GetCategory>>

    @POST("/user/login/{id}/{pw}")
    fun signIn(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetStatus>

    @POST("/user/signUp")
    fun signUp(
        @Path("id") id: String,
        @Path("pw") pw: String
    ): Call<GetStatus>

    @GET("/user/{id}")
    fun getUserID(
        @Path("id") id: String
    ): Call<GetID>
}