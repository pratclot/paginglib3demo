package com.pratclot.paginglib3demo.data

import com.google.gson.annotations.Expose

data class CatImage(
    @Expose(serialize = false, deserialize = false)
    val breeds: List<String>,
    @Expose(serialize = false, deserialize = false)
    val categories: List<CatCategory>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)