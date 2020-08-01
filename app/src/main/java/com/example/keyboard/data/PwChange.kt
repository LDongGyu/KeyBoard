package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class PwChange(
    @SerializedName("userId")
    var id: Int,
    @SerializedName("currentPw")
    var currentPw: String,
    @SerializedName("newPw")
    var newPw: String
)