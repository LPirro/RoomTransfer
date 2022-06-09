package com.lpirro.roomtransfer.data.persistance

import com.lpirro.roomtransfer.util.MockUtil
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class RoomsDaoTest : RoomTransferDatabaseTest() {

    private lateinit var roomsDao: RoomsDao

    @Before
    fun setup() {
        roomsDao = database.roomsDao()
    }

    @Test
    fun insertAndLoadRoomTest() = runBlocking {
        val mockData = MockUtil.mockLocalRoomList()
        roomsDao.insertAll(mockData)

        val resultsFromDatabase = roomsDao.getAll()
        assertEquals(resultsFromDatabase.first().id, mockData.first().id)
    }

    @Test
    fun deleteAllRoomTests() = runBlocking {
        val mockData = MockUtil.mockLocalRoomList()
        roomsDao.insertAll(mockData)

        roomsDao.deleteAll()
        val roomsOnDatabase = roomsDao.getAll()
        assertEquals(roomsOnDatabase.size, 0)
    }
}
