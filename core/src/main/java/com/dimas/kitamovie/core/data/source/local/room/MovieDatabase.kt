package com.dimas.kitamovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun tourismDao(): MovieDao
}