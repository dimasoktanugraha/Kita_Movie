package com.dimas.kitamovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieRawResponse (

    @field:SerializedName("results")
    var results: List<MovieResponse>

    )