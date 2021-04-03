package com.dimas.kitamovie.core.data.source.remote.network

import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieRawResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewRawResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?language=en-US")
    suspend fun getNowPlaying(
        @Query("api_key") api: String
    ): MovieRawResponse

    @GET("movie/popular?language=en-US")
    suspend fun getPopular(
        @Query("api_key") api: String
    ): MovieRawResponse

    @GET("movie/top_rated?language=en-US")
    suspend fun getTop(
        @Query("api_key") api: String
    ): MovieRawResponse

    @GET("movie/{id}?language=en-US")
    suspend fun getDetail(
        @Path("id") id: String,
        @Query("api_key") api: String
    ): DetailResponse

    @GET("movie/{id}/reviews?language=en-US")
    suspend fun getReview(
        @Path("id") id: String,
        @Query("api_key") api: String
    ): ReviewRawResponse

}
