package com.hugomora.upaxtmdbhugomora.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import com.hugomora.upaxtmdbhugomora.databinding.FragmentMediaBinding
import com.hugomora.upaxtmdbhugomora.helpers.ViewStatesHelper
import com.hugomora.upaxtmdbhugomora.network.services.MovieService
import com.hugomora.upaxtmdbhugomora.ui.adapters.MovieItemsAdapter
import com.hugomora.upaxtmdbhugomora.viewmodels.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class MediaFragment: Fragment() {
    private lateinit var binding: FragmentMediaBinding

    private val movieViewModel: MovieViewModel by viewModels()

    private var movieItemsAdapter: MovieItemsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrincipalFunctions()
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.loadMovies()
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        ViewStatesHelper.showLoadingState(binding)
        movieViewModel.getMovieListObject().observe(viewLifecycleOwner, { setData(it) })
        context?.let {
            binding.recyclerView.layoutManager = LinearLayoutManager(it)
            movieItemsAdapter = MovieItemsAdapter(it, ArrayList())
        }
    }

    private fun setData(listUpdated: List<MovieModel>) {
        val newList = ArrayList<MovieModel>().apply { addAll(listUpdated) }
        if (newList.isEmpty()) {
            ViewStatesHelper.showEmptyState(binding)
        } else {
            ViewStatesHelper.showContentState(binding)
            movieItemsAdapter?.setData(newList)
        }
    }

    companion object {
        fun newInstance(): MediaFragment {
            return MediaFragment().apply {
                arguments = Bundle()
            }
        }
    }
}