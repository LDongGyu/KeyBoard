package com.example.keyboard.KeyList

import android.graphics.drawable.Icon

data class KeyItem(
    var icon: Icon = Icon.createWithFilePath(""),
    var title: String = "네이버",
    var categroy: String = "홈페이지"
)