package com.dimas.kitamovie.di

import com.dimas.kitamovie.core.domain.usecase.MovieInteractor
import com.dimas.kitamovie.core.domain.usecase.MovieUseCase
import com.dimas.kitamovie.ui.detail.DetailViewModel
import com.dimas.kitamovie.ui.main.favorite.FavoriteViewModel
import com.dimas.kitamovie.ui.main.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}