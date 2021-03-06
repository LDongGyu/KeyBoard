package com.example.keyboard.api

import com.example.keyboard.data.*
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.CategoryList.CategoryListItem
import retrofit2.Call
import retrofit2.http.*

interface DBService {
    @GET("/item/read/{id}")
    fun getItem(
        @Path("id") id: Int
    ): Call<List<KeyItem>>

    @POST("/item/create")
    fun itemCreate(
        @Body item: KeyItem
    ): Call<GetStatus>

    @POST("/item/update")
    fun itemUpdate(
        @Body item: KeyItem
    ): Call<GetStatus>

    @POST("/item/delete")
    fun itemDelete(
        @Body item: KeyItem
    ): Call<GetStatus>

    @POST("/item/read/child")
    fun childItemRead(
        @Body category: Category
    ): Call<List<KeyItem>>

    @GET("/category/read/{id}")
    fun getCategory(
        @Path("id") id: Int
    ): Call<List<GetCategory>>

    @POST("/category/create")
    fun categoryCreate(
        @Body category: Category
    ): Call<GetStatus>

    @POST("/category/update")
    fun categoryUpdate(
        @Body item: Category
    ): Call<GetStatus>

    @POST("/category/delete")
    fun categoryDelete(
        @Body item: Category
    ): Call<GetStatus>

    @POST("/user/signIn")
    fun signIn(
        @Body data: ID
    ): Call<GetStatus>

    @POST("/user/signUp")
    fun signUp(
        @Body data: ID
    ): Call<GetStatus>

    @GET("/user/{id}")
    fun getUserID(
        @Path("id") id: String
    ): Call<GetID>

    @POST("/user/pwChange")
    fun pwChange(
        @Body data: PwChange
    ): Call<GetStatus>
}