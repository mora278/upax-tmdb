package com.hugomora.upaxtmdbhugomora.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import com.hugomora.upaxtmdbhugomora.helpers.GeneralSystemHelper
import com.hugomora.upaxtmdbhugomora.network.services.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val api = MovieService()

    private val _movieList = MutableLiveData<List<MovieModel>>()
    private fun setMovieList(newList: List<MovieModel>) = _movieList.postValue(newList)
    private fun getMovieList() =_movieList.value ?: emptyList()
    fun getMovieListObject() =_movieList

    fun loadMovies() = viewModelScope.launch(Dispatchers.Main) {
        api.getMovie(2)?.let {
            val newList = ArrayList<MovieModel>().apply { addAll(getMovieList()); add(it) }
            GeneralSystemHelper.showShortMessage("new list ${newList.size}")
            setMovieList(newList)
        }
    }
}