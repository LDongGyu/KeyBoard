package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class GetStatus(
    @SerializedName("status")
    val status: String
)