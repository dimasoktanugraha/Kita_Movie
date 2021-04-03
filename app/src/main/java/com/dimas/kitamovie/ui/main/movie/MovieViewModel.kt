package com.dimas.kitamovie.ui.main.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getNowPlaying() : LiveData<Resource<List<MovieResponse>>> = movieUseCase.getNowPlaying().asLiveData()

    fun getPopular() : LiveData<Resource<List<MovieResponse>>> = movieUseCase.getPopular().asLiveData()

    fun getTop() : LiveData<Resource<List<MovieResponse>>> = movieUseCase.getTop().asLiveData()

}