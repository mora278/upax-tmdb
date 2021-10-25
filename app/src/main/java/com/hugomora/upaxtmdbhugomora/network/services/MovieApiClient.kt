package com.hugomora.upaxtmdbhugomora.network.services

import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {
    private companion object {
        private const val API_KEY = "41022b13a343d9facfd5d287fa36868c" // Any api key should be stored at local.properties file
    }

    @GET("{movieId}?api_key=$API_KEY")
    suspend fun getMovie(@Path("movieId") movieId: Int): Response<MovieModel>
}