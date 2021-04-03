package com.dimas.kitamovie.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var movieId: Int,
    var title: String,
    var overview: String,
    var vote_average: Float,
    var release_date: String,
    var poster_path: String,
    var backdrop_path: String,
    var isFavorite: Boolean
): Parcelable
