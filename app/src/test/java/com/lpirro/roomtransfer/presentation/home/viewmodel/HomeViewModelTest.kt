package com.lpirro.roomtransfer.presentation.home.viewmodel

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.lpirro.roomtransfer.domain.usecase.BookRoomUseCase
import com.lpirro.roomtransfer.domain.usecase.GetRoomsUseCase
import com.lpirro.roomtransfer.presentation.home.viewmodel.HomeViewModel.HomeEvents
import com.lpirro.roomtransfer.presentation.home.viewmodel.HomeViewModel.HomeUiState
import com.lpirro.roomtransfer.util.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)

class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context: Application = ApplicationProvider.getApplicationContext()

    private lateinit var viewModel: HomeViewModel

    private var getRoomsUseCase: GetRoomsUseCase = mock()
    private var bookRoomUseCase: BookRoomUseCase = mock()

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = HomeViewModel(context, getRoomsUseCase, bookRoomUseCase)
    }

    @Test
    fun fetchHomeScreenWithSuccessTest() = runTest {
        val mockHomeScreenData = MockUtil.mockRoomList()
        whenever(getRoomsUseCase()).thenReturn(flow { emit(mockHomeScreenData) })

        viewModel.getRooms()

        viewModel.homeScreenUiState.test {
            val emission = awaitItem() as HomeUiState.Success
            assertEquals(emission.rooms, mockHomeScreenData)
        }
    }

    @Test
    fun fetchHomeScreenWithErrorTest() = runTest {
        whenever(getRoomsUseCase()).thenReturn(null)
        viewModel.getRooms()

        viewModel.homeScreenUiState.test {
            val emission = awaitItem()
            assertEquals(emission, HomeUiState.Error)
        }
    }

    @Test
    fun bookRoomSuccessTest() = runTest {
        viewModel.bookRoom()
        viewModel.homeScreenEvents.test {
            val emission = awaitItem()
            assertEquals(emission, HomeEvents.ShowToast("Room booked successfully"))
        }
    }

    @Test
    fun bookRoomErrorTest() = runTest {
        given(bookRoomUseCase()).willAnswer { throw UnknownHostException() }

        viewModel.bookRoom()
        viewModel.homeScreenEvents.test {
            val emission = awaitItem()
            assertEquals(
                emission,
                HomeEvents.ShowToast("Unable to book this room. Check your network connection and try again")
            )
        }
    }
}
