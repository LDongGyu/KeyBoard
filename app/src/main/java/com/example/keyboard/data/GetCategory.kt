package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class GetCategory(
    @SerializedName("title")
    val category: String,
    @SerializedName("etc")
    val etc: String
)