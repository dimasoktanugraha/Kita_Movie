package com.dimas.kitamovie.core.utils

import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse

object DataMapper {

    fun mapDetailDomainToEntity(input: DetailResponse) = FavoriteEntity(
            movieId = input.movieId,
            title = input.title,
            overview = input.overview,
            vote_average = input.vote_average,
            release_date = input.release_date,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path
    )

}