package com.example.keyboard.feature.CategoryList

import com.google.gson.annotations.SerializedName

data class CategoryListItem (
    var category: String,
    var etc: String,
    var child: List<String>
)