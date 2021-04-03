package com.dimas.kitamovie.core.domain.repository

import android.util.Log
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.network.ApiResponse
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewResponse
import com.dimas.kitamovie.core.domain.model.Movie
import com.dimas.kitamovie.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IMovieRepository {

    fun getNowPlaying(): Flow<Resource<List<MovieResponse>>>

    fun getPopular(): Flow<Resource<List<MovieResponse>>>

    fun getTop(): Flow<Resource<List<MovieResponse>>>

    fun getDetail(id: String): Flow<Resource<DetailResponse>>

    fun getReview(id: String): Flow<Resource<List<ReviewResponse>>>

    fun isFavorite(id: String): Flow<FavoriteEntity>

    fun getFavoriteMovie(): Flow<List<FavoriteEntity>>

    fun insertMovie(movie: DetailResponse)

    fun deleteMovie(movie: DetailResponse)



}