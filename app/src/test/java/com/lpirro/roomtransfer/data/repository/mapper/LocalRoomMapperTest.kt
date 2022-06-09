package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.util.MockUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalRoomMapperTest {

    private lateinit var localRoomMapper: LocalRoomMapper

    @Before
    fun setup() {
        localRoomMapper = LocalRoomMapperImpl()
    }

    @Test
    fun remoteRoomToRoomMapsCorrectly() {
        val remoteRoomList = MockUtil.mockRemoteRoomList()
        val result = remoteRoomList.map { localRoomMapper.mapRemoteToLocalRoom(it) }

        val expectedMappedList = MockUtil.mockLocalRoomList()

        result.forEachIndexed { index, room ->
            Assert.assertEquals(room.name, expectedMappedList[index].name)
            Assert.assertEquals(room.spots, expectedMappedList[index].spots)
            Assert.assertEquals(room.thumbnail, expectedMappedList[index].thumbnail)
        }
    }
}
