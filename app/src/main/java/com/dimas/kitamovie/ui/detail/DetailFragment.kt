package com.dimas.kitamovie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dimas.kitamovie.R
import com.dimas.kitamovie.core.data.Resource
import com.dimas.kitamovie.core.data.source.remote.response.DetailResponse
import com.dimas.kitamovie.core.utils.Constant
import com.dimas.kitamovie.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment: Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private var isFavorite = false
    private lateinit var detailData : DetailResponse

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val id by lazy {
        DetailFragmentArgs.fromBundle(requireArguments()).id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val reviewAdapter = ReviewAdapter()
            with(binding.rvReview) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = reviewAdapter
            }

            binding.detailBack.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            detailViewModel.isFavorite(id.toString()).observe(viewLifecycleOwner, {data ->
                isFavorite = (data != null)
                setStatusFavorite(isFavorite)
            })

            detailViewModel.getDetail(id).observe(viewLifecycleOwner, { detail ->
                if (detail != null) {
                    when (detail) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (detail.data != null) {
                                detailData = detail.data!!
                                binding.progressBar.visibility = View.GONE
                                binding.detailTitle.text = detailData.title
                                binding.detailOverview.text = detailData.overview
                                binding.detailRate.rating = detailData.vote_average ?: 0f
                                binding.detailRateNumber.text =
                                    detailData.vote_average.toString()

                                setImage(detailData.backdrop_path!!, binding.detailBackdrop)
                                setImage(detailData.poster_path, binding.detailPoster)

                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })

            detailViewModel.getReview(id).observe(viewLifecycleOwner, { review ->
                if (review != null) {
                    when (review) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (review.data != null) {
                                reviewAdapter.setData(review.data)
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })

            binding.detailFavorite.setOnClickListener {

                if (isFavorite){
                    detailViewModel.deleteMovie(detailData)
                }else{
                    detailViewModel.insertMovie(detailData)
                }

                isFavorite = !isFavorite
                setStatusFavorite(isFavorite)
            }
        }
    }
    private fun setImage(link: String, view: ImageView) {
        Glide.with(requireActivity())
            .load(Constant.IMAGE_URL + link)
            .into(view)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detailFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.detailFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}