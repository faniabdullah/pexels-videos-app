package com.faniabdullah.pexels_video.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.faniabdullah.pexels_video.DetailVideos
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.utils.Resource
import com.faniabdullah.pexels_video.databinding.FragmentHomeBinding
import com.faniabdullah.pexels_video.ui.adapter.Adapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val videosAdapter: Adapter by lazy {
        Adapter(::showDetail)
    }

    private fun showDetail(videosEntity: VideosEntity) {
        val detailIntent = Intent(requireContext(), DetailVideos::class.java)
        detailIntent.putExtra(DetailVideos.KEY_DETAIL_VIDEOS, videosEntity)
        startActivity(detailIntent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = videosAdapter
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        homeViewModel.videos.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    if (it.data != null) {
                        videosAdapter.setData(it.data)
                    }
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}