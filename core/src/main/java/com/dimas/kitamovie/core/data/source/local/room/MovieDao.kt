package com.dimas.kitamovie.core.data.source.local.room

import androidx.room.*
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite where movieId = :id")
    fun isFavorite(id: String): Flow<FavoriteEntity>

    @Query("SELECT * FROM favorite")
    fun getFavoriteMovie(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: FavoriteEntity)

    @Delete
    fun deleteMovie(movie: FavoriteEntity)

}