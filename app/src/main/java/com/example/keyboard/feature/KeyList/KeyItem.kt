package com.example.keyboard.feature.KeyList

import android.graphics.drawable.Icon

data class KeyItem(
    var icon: Icon = Icon.createWithFilePath(""),
    var title: String = "네이버",
    var categroy: String = "홈페이지"
//    var id: String = "id",
//    var pw: String = "pw",
//    var url: String = "url",
//    var etc: String = "etc"
)