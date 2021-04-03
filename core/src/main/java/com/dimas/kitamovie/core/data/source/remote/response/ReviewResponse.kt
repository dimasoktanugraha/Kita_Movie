package com.dimas.kitamovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse (

    @field:SerializedName("author")
    var author: String,

    @field:SerializedName("content")
    var content: String,

    @field:SerializedName("created_at")
    var created_at: String,

)
