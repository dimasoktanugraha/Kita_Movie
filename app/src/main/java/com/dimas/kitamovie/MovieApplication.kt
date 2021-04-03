package com.dimas.kitamovie

import android.app.Application
import com.dimas.kitamovie.core.di.databaseModule
import com.dimas.kitamovie.core.di.networkModule
import com.dimas.kitamovie.core.di.repositoryModule
import com.dimas.kitamovie.di.useCaseModule
import com.dimas.kitamovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}