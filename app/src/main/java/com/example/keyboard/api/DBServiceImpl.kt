package com.example.keyboard.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DBServiceImpl {
    private const val BASE_URL = "http://192.168.1.103:3000"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    val service: DBService = retrofit.create(DBService::class.java)
}