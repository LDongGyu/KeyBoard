package com.example.keyboard.feature.CategoryList

import com.example.keyboard.feature.KeyList.KeyItem
import com.google.gson.annotations.SerializedName

data class CategoryListItem (
    var category: String,
    var etc: String,
    var child: List<KeyItem>
)