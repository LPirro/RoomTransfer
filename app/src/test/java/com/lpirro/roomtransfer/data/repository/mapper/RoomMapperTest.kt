package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.util.MockUtil
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RoomMapperTest {

    private lateinit var roomMapper: RoomMapper

    @Before
    fun setup() {
        roomMapper = RoomMapperImpl()
    }

    @Test
    fun localRoomToRoomMapsCorrectly() {
        val localRoomList = MockUtil.mockLocalRoomList()
        val result = localRoomList.map { roomMapper.mapLocalToRoom(it) }

        val expectedMappedList = MockUtil.mockRoomList()

        result.forEachIndexed { index, room ->
            assertEquals(room.name, expectedMappedList[index].name)
            assertEquals(room.spots, expectedMappedList[index].spots)
            assertEquals(room.thumbnail, expectedMappedList[index].thumbnail)
        }
    }
}
