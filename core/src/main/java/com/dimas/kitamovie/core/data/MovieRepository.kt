package com.dimas.kitamovie.core.data


import android.util.Log
import com.dimas.kitamovie.core.data.source.local.LocalDataSource
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.RemoteDataSource
import com.dimas.kitamovie.core.data.source.remote.network.ApiResponse
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewResponse
import com.dimas.kitamovie.core.domain.model.Movie
import com.dimas.kitamovie.core.domain.repository.IMovieRepository
import com.dimas.kitamovie.core.utils.AppExecutors
import com.dimas.kitamovie.core.utils.DataMapper
import kotlinx.coroutines.flow.*

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getNowPlaying(): Flow<Resource<List<MovieResponse>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getNowPlaying().first()) {
            is ApiResponse.Success -> {
                Log.d("GET", "repository success")
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<List<MovieResponse>>())
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<MovieResponse>>(apiResponse.errorMessage)
                )
            }
        }
    }

    override fun getPopular(): Flow<Resource<List<MovieResponse>>>  = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getPopular().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<List<MovieResponse>>())
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<MovieResponse>>(apiResponse.errorMessage)
                )
            }
        }
    }

    override fun getTop(): Flow<Resource<List<MovieResponse>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getTop().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<List<MovieResponse>>())
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<MovieResponse>>(apiResponse.errorMessage)
                )
            }
        }
    }

    override fun getDetail(id: String): Flow<Resource<DetailResponse>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getDetail(id).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<DetailResponse>())
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<DetailResponse>(apiResponse.errorMessage)
                )
            }
        }
    }

    override fun getReview(id: String): Flow<Resource<List<ReviewResponse>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getReview(id).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<List<ReviewResponse>>())
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<ReviewResponse>>(apiResponse.errorMessage)
                )
            }
        }
    }

    override fun isFavorite(id: String): Flow<FavoriteEntity> = localDataSource.isFavorite(id)

    override fun getFavoriteMovie(): Flow<List<FavoriteEntity>> = localDataSource.getFavoriteMovie()

    override fun insertMovie(movie: DetailResponse) {
        val fav = DataMapper.mapDetailDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.insertMovie(fav) }
    }

    override fun deleteMovie(movie: DetailResponse) {
        val fav = DataMapper.mapDetailDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.deleteMovie(fav) }
    }

}

