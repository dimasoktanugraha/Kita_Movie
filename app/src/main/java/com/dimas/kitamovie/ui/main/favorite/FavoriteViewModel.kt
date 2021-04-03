package com.dimas.kitamovie.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>> = movieUseCase.getFavoriteMovie().asLiveData()


}