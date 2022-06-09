package com.lpirro.roomtransfer.util

import com.lpirro.roomtransfer.data.network.model.RemoteRoom
import com.lpirro.roomtransfer.data.network.model.RemoteRoomResponse
import com.lpirro.roomtransfer.data.persistance.model.LocalRoom
import com.lpirro.roomtransfer.domain.model.Room

private const val THUMBNAIL_URL = "www.thumbnail.url"

object MockUtil {

    fun mockRoomList(): List<Room> {
        return listOf(
            Room("Room 1", 10, THUMBNAIL_URL),
            Room("Room 2", 20, THUMBNAIL_URL),
            Room("Room 3", 40, THUMBNAIL_URL),
            Room("Room 4", 50, THUMBNAIL_URL),
            Room("Room 5", 0, THUMBNAIL_URL)
        )
    }

    fun mockRemoteRoomResponse() = RemoteRoomResponse(mockRemoteRoomList())

    fun mockRemoteRoomList(): List<RemoteRoom> {
        return listOf(
            RemoteRoom("Room 1", 10, THUMBNAIL_URL),
            RemoteRoom("Room 2", 20, THUMBNAIL_URL),
            RemoteRoom("Room 3", 40, THUMBNAIL_URL),
            RemoteRoom("Room 4", 50, THUMBNAIL_URL),
            RemoteRoom("Room 5", 0, THUMBNAIL_URL)
        )
    }

    fun mockLocalRoomList(): List<LocalRoom> {
        return listOf(
            LocalRoom(1, "Room 1", 10, THUMBNAIL_URL),
            LocalRoom(2, "Room 2", 20, THUMBNAIL_URL),
            LocalRoom(3, "Room 3", 40, THUMBNAIL_URL),
            LocalRoom(4, "Room 4", 50, THUMBNAIL_URL),
            LocalRoom(5, "Room 5", 0, THUMBNAIL_URL)
        )
    }
}
