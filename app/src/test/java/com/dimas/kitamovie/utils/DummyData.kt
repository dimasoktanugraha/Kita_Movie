package com.dimas.kitamovie.utils

import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import java.util.*

class DummyData {

    companion object{
        fun generateDummyMovies(): ArrayList<MovieResponse>? {
            val movie: ArrayList<MovieResponse> = ArrayList<MovieResponse>()
            movie.add(
                MovieResponse(
                    429617,
                    "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                    "Spider-Man: Far from Home",
                    7.8,
                    "2019-06-28",
                    "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
                    "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
                )
            )
            movie.add(
                MovieResponse(
                    1263,
                    "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "Toy Story 4",
                    7.8,
                    "2019-06-19",
                    "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                    "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                )
            )
            return movie
        }
    }


}