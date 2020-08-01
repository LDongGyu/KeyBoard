package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class ID(
    @SerializedName("id")
    var id: String,
    @SerializedName("pw")
    var pw: String
)