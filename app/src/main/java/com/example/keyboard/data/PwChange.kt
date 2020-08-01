package com.example.keyboard.data

import com.google.gson.annotations.SerializedName

data class PwChange(
    @SerializedName("userid")
    var id: Int,
    @SerializedName("currentpw")
    var currentPw: String,
    @SerializedName("newpw")
    var newPw: String
)