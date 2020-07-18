package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class GetUserData(
    @SerializedName("status")
    val status: String
)