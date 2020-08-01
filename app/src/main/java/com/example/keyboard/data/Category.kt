package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("title")
    val category: String,
    @SerializedName("etc")
    val etc: String,
    @SerializedName("id")
    val id: Int, // userid
    @SerializedName("beforetitle")
    val beforetitle: String = ""
)