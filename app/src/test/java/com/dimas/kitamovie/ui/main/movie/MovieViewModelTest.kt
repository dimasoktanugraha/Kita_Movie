package com.dimas.kitamovie.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.domain.usecase.MovieUseCase
import com.dimas.kitamovie.utils.DummyData
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.Result.Companion.success

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private var viewModel: MovieViewModel? = null

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieResponse>>>


    @Before
    @Throws(Exception::class)
    fun setUp() {
        viewModel = MovieViewModel(movieUseCase)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    fun testGetNowPlaying() {
//        val dummy = Resource.Success(DummyData.generateDummyMovies()!!.toList())
//        val dataFlow = flow{
//            emit(dummy)
//        }
//
//        Mockito.`when`(movieUseCase.getNowPlaying()).thenReturn(dataFlow)
//        verify(movieUseCase).getNowPlaying()

//        val observer = Mockito.mock(Observer::class.java)

//        viewModel!!.getNowPlaying().observeForever(observer)
//        verify(observer).onChanged(dummy)
    }

    fun testGetPopular() {}

    fun testGetTop() {}
}