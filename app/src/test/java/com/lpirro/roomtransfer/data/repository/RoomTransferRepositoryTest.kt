package com.lpirro.roomtransfer.data.repository

import com.lpirro.roomtransfer.data.network.RoomTransferService
import com.lpirro.roomtransfer.data.persistance.RoomsDao
import com.lpirro.roomtransfer.data.repository.mapper.LocalRoomMapper
import com.lpirro.roomtransfer.data.repository.mapper.RoomMapper
import com.lpirro.roomtransfer.util.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
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
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class RoomTransferRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var repository: RoomTransferRepository

    private var roomsDao: RoomsDao = mock()
    private var service: RoomTransferService = mock()

    @Inject
    lateinit var localRoomMapper: LocalRoomMapper

    @Inject
    lateinit var roomMapper: RoomMapper

    @Before
    fun setup() {
        hiltRule.inject()
        repository = RoomTransferRepositoryImpl(service, roomsDao, localRoomMapper, roomMapper)
    }

    @Test
    fun fetchRoomsFromNetworkTest() = runTest {
        val mockData = MockUtil.mockLocalRoomList()
        whenever(service.fetchRooms()).thenReturn(MockUtil.mockRemoteRoomResponse())
        whenever(roomsDao.getAll()).thenReturn(mockData)

        val result = repository.getRooms().single()
        assertEquals(result.size, 5)
    }

    @Test
    fun fetchRoomsFromDatabaseTest() = runTest {
        val mockData = MockUtil.mockLocalRoomList()
        given(service.fetchRooms()).willAnswer { throw UnknownHostException() }
        whenever(roomsDao.getAll()).thenReturn(mockData)

        val expectedResult = repository.getRooms().single()
        val resultFromDatabase = roomsDao.getAll()

        expectedResult.forEachIndexed { index, room ->
            assertEquals(room.name, resultFromDatabase[index].name)
            assertEquals(room.spots, resultFromDatabase[index].spots)
            assertEquals(room.thumbnail, resultFromDatabase[index].thumbnail)
        }
    }
}
