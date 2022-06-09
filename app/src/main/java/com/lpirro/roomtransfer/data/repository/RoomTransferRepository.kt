package com.lpirro.roomtransfer.data.repository

import com.lpirro.roomtransfer.domain.model.Room
import kotlinx.coroutines.flow.Flow

interface RoomTransferRepository {
    suspend fun getRooms(): Flow<List<Room>>
    suspend fun bookRoom()
}
