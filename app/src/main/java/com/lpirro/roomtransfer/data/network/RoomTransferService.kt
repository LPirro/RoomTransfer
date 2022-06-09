package com.lpirro.roomtransfer.data.network

import com.lpirro.roomtransfer.data.network.model.RemoteRoomResponse
import retrofit2.http.GET

interface RoomTransferService {

    @GET("rooms.json")
    suspend fun fetchRooms(): RemoteRoomResponse

    @GET("bookRoom.json")
    suspend fun bookRoom()
}
