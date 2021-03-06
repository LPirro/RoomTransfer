package com.lpirro.roomtransfer.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.roomtransfer.R
import com.lpirro.roomtransfer.domain.model.Room
import com.lpirro.roomtransfer.domain.usecase.BookRoomUseCase
import com.lpirro.roomtransfer.domain.usecase.GetRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val context: Application,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val bookRoomUseCase: BookRoomUseCase
) : ViewModel(), HomeViewModelContract {

    private val _homeScreenUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeScreenUiState: StateFlow<HomeUiState> = _homeScreenUiState
    private val _homeScreenEvents = Channel<HomeEvents>(Channel.BUFFERED)
    val homeScreenEvents = _homeScreenEvents.receiveAsFlow()

    init {
        getRooms()
    }

    fun retry() {
        getRooms()
    }

    override fun getRooms() = viewModelScope.launch {
        try {
            getRoomsUseCase().collect { rooms ->
                _homeScreenUiState.value = HomeUiState.Success(rooms)
            }
        } catch (e: Exception) {
            _homeScreenUiState.value = HomeUiState.Error
        }
    }

    override fun bookRoom() = viewModelScope.launch {
        try {
            bookRoomUseCase()
            val text = context.getString(R.string.booking_room_success)
            _homeScreenEvents.send(HomeEvents.ShowToast(text))
        } catch (e: Exception) {
            val text = context.getString(R.string.booking_room_error)
            _homeScreenEvents.send(HomeEvents.ShowToast(text))
        }
    }

    sealed class HomeUiState {
        object Loading : HomeUiState()
        data class Success(val rooms: List<Room>) : HomeUiState()
        object Error : HomeUiState()
    }

    sealed class HomeEvents {
        data class ShowToast(val text: String) : HomeEvents()
    }
}
