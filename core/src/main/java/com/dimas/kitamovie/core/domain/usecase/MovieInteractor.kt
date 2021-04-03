package com.dimas.kitamovie.core.domain.usecase

import android.util.Log
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewResponse
import com.dimas.kitamovie.core.domain.model.Movie
import com.dimas.kitamovie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getNowPlaying(): Flow<Resource<List<MovieResponse>>> = movieRepository.getNowPlaying()

    override fun getPopular(): Flow<Resource<List<MovieResponse>>> = movieRepository.getPopular()

    override fun getTop(): Flow<Resource<List<MovieResponse>>> = movieRepository.getTop()

    override fun getDetail(id: String): Flow<Resource<DetailResponse>> = movieRepository.getDetail(id)

    override fun getReview(id: String): Flow<Resource<List<ReviewResponse>>> = movieRepository.getReview(id)

    override fun isFavorite(id: String): Flow<FavoriteEntity> = movieRepository.isFavorite(id)

    override fun getFavoriteMovie(): Flow<List<FavoriteEntity>> = movieRepository.getFavoriteMovie()

    override fun insertMovie(movie: DetailResponse) = movieRepository.insertMovie(movie)

    override fun deleteMovie(movie: DetailResponse) = movieRepository.deleteMovie(movie)
}