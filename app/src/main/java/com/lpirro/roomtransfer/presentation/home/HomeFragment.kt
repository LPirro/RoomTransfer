package com.lpirro.roomtransfer.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lpirro.roomtransfer.databinding.FragmentHomeBinding
import com.lpirro.roomtransfer.extensions.hide
import com.lpirro.roomtransfer.extensions.isOrientationPortrait
import com.lpirro.roomtransfer.extensions.show
import com.lpirro.roomtransfer.presentation.base.BaseFragment
import com.lpirro.roomtransfer.presentation.home.adapter.RoomAdapter
import com.lpirro.roomtransfer.presentation.home.adapter.SpacingItemDecoration
import com.lpirro.roomtransfer.presentation.home.viewmodel.HomeViewModel
import com.lpirro.roomtransfer.presentation.home.viewmodel.HomeViewModel.HomeEvents
import com.lpirro.roomtransfer.presentation.home.viewmodel.HomeViewModel.HomeUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var roomAdapter: RoomAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        registerObservers()
        binding.errorView.retryClickListener = { viewModel.retry() }
    }

    private fun onUiUpdate(uiState: HomeUiState) {
        resetViews()
        when (uiState) {
            HomeUiState.Error -> binding.errorView.show()
            HomeUiState.Loading -> binding.progressBar.show()
            is HomeUiState.Success -> roomAdapter.setData(uiState.rooms)
        }
    }

    private fun onEvent(homeEvents: HomeEvents) {
        when (homeEvents) {
            is HomeEvents.ShowToast -> showToast(homeEvents.text)
        }
    }

    private fun bookRoom() {
        viewModel.bookRoom()
    }

    private fun setupRecyclerView() {
        val layManager: RecyclerView.LayoutManager
        val itemDecoration: RecyclerView.ItemDecoration

        if (isOrientationPortrait()) {
            layManager = LinearLayoutManager(requireContext())
            itemDecoration = SpacingItemDecoration(1, true)
        } else {
            layManager = GridLayoutManager(requireContext(), 2)
            itemDecoration = SpacingItemDecoration(2, true)
        }

        roomAdapter = RoomAdapter(::bookRoom)
        binding.roomRecyclerView.apply {
            layoutManager = layManager
            adapter = roomAdapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.homeScreenUiState.collect { onUiUpdate(it) } }
                launch { viewModel.homeScreenEvents.collect { onEvent(it) } }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun resetViews() {
        binding.errorView.hide()
        binding.progressBar.hide()
    }
}
