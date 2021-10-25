package com.hugomora.upaxtmdbhugomora.network.services

import com.hugomora.upaxtmdbhugomora.helpers.RetrofitHelper
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import com.hugomora.upaxtmdbhugomora.viewmodels.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.await

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovie(movieId: Int): MovieModel? = withContext(Dispatchers.IO) {
        return@withContext retrofit.create(MovieApiClient::class.java).getMovie(movieId).body()
    }
}