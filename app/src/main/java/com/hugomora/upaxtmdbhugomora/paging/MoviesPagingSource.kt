package com.hugomora.upaxtmdbhugomora.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hugomora.upaxtmdbhugomora.network.services.MovieService
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.MOVIE_STARTING_PAGE_INDEX
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.NETWORK_PAGE_SIZE
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(private val movieService: MovieService): PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        TODO("Not yet implemented")
    }

    /*
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageIndex = params.key ?: MOVIE_STARTING_PAGE_INDEX
        return try {
            val movie = movieService.getMovie(pageIndex)
            val newList = ArrayList<MovieModel>().apply { movie?.let { add(it) } }
            val nextKey = if (newList.isEmpty()) null else pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = newList,
                prevKey = if (pageIndex == MOVIE_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

     */
}