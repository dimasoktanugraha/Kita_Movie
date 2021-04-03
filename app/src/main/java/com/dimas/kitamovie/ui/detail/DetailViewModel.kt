package com.dimas.kitamovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewResponse
import com.dimas.kitamovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetail(id: Long) : LiveData<Resource<DetailResponse>> = movieUseCase.getDetail(id.toString()).asLiveData()

    fun getReview(id: Long) : LiveData<Resource<List<ReviewResponse>>> = movieUseCase.getReview(id.toString()).asLiveData()

    fun isFavorite(id: String): LiveData<FavoriteEntity> = movieUseCase.isFavorite(id).asLiveData()

    fun insertMovie(movie: DetailResponse) = movieUseCase.insertMovie(movie)

    fun deleteMovie(movie: DetailResponse) = movieUseCase.deleteMovie(movie)
}