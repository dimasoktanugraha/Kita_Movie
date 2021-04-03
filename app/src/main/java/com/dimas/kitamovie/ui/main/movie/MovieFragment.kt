package com.dimas.kitamovie.ui.main.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.kitamovie.R
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.databinding.FragmentMovieBinding
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment: Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val nowAdapter = MovieAdapter()
    private val popAdapter = MovieAdapter()
    private val topAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            nowAdapter.onItemClick = { selectedData ->
                val toDetail = MovieFragmentDirections.actionNavigationMovieToDetailFragment(selectedData.movieId)
                Navigation.findNavController(view).navigate(toDetail)
            }
            popAdapter.onItemClick = { selectedData ->
                val toDetail = MovieFragmentDirections.actionNavigationMovieToDetailFragment(selectedData.movieId)
                Navigation.findNavController(view).navigate(toDetail)
            }
            topAdapter.onItemClick = { selectedData ->
                val toDetail = MovieFragmentDirections.actionNavigationMovieToDetailFragment(selectedData.movieId)
                Navigation.findNavController(view).navigate(toDetail)
            }

            with(binding.rvMovieNow) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = nowAdapter
            }

            with(binding.rvMovieTop) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = topAdapter
            }

            with(binding.rvMoviePop) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = popAdapter
            }

            getData()

//            binding.movieSearch.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    movieAdapter.filter.filter(p0)
//                }
//
//                override fun afterTextChanged(p0: Editable?) {}
//            })

        }


    }

    private fun getData() {
        movieViewModel.getNowPlaying().observe(viewLifecycleOwner, {movie ->
            if (movie != null){
                when(movie) {
                    is Resource.Loading -> {
                        setShimmer(binding.shimmerMovieNow, true)
                    }
                    is Resource.Success -> {
                        nowAdapter.setData(movie.data)
                        setShimmer(binding.shimmerMovieNow, false)
                    }
                    is Resource.Error -> {
                        setShimmer(binding.shimmerMovieNow, false)
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        movieViewModel.getTop().observe(viewLifecycleOwner, {movie ->
            if (movie != null){
                when(movie) {
                    is Resource.Loading -> {
                        setShimmer(binding.shimmerMovieTop, true)
                    }
                    is Resource.Success -> {
                        topAdapter.setData(movie.data)
                        setShimmer(binding.shimmerMovieTop, false)
                    }
                    is Resource.Error -> {
                        setShimmer(binding.shimmerMovieTop, false)
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        movieViewModel.getPopular().observe(viewLifecycleOwner, {movie ->
            if (movie != null){
                when(movie) {
                    is Resource.Loading -> {
                        setShimmer(binding.shimmerMoviePop, true)
                    }
                    is Resource.Success -> {
                        popAdapter.setData(movie.data)
                        setShimmer(binding.shimmerMoviePop, false)
                    }
                    is Resource.Error -> {
                        setShimmer(binding.shimmerMoviePop, false)
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    private fun setShimmer(shimmer: ShimmerFrameLayout, show: Boolean) {
        if (show){
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        }else{
            shimmer.visibility = View.GONE
            shimmer.stopShimmer()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}