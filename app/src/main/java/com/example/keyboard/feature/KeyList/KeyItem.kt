package com.example.keyboard.feature.KeyList

import android.graphics.drawable.Icon
import com.google.gson.annotations.SerializedName

data class KeyItem(
    var icon: Icon = Icon.createWithFilePath(""),
    @SerializedName("title")
    var title: String = "네이버",
    @SerializedName("category")
    var categroy: String = "홈페이지",
    @SerializedName("id")
    var id: String = "id",
    @SerializedName("pw")
    var pw: String = "pw",
    @SerializedName("url")
    var url: String = "url",
    @SerializedName("etc")
    var etc: String = "etc",
    @SerializedName("userId")
    var userId: Int = 1,
    @SerializedName("beforeTitle")
    var beforeTitle: String = "카카오"
)