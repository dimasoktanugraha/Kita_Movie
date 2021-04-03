package com.dimas.kitamovie.core.data.source.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao)  {

    fun isFavorite(id: String): Flow<FavoriteEntity> = movieDao.isFavorite(id)

    fun getFavoriteMovie(): Flow<List<FavoriteEntity>> = movieDao.getFavoriteMovie()

    fun insertMovie(movie: FavoriteEntity) = movieDao.insertMovie(movie)

    fun deleteMovie(movie: FavoriteEntity) = movieDao.deleteMovie(movie)

}