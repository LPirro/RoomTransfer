package com.lpirro.roomtransfer.domain.usecase

import com.lpirro.roomtransfer.data.repository.RoomTransferRepository
import com.lpirro.roomtransfer.domain.model.Room
import kotlinx.coroutines.flow.Flow

class GetRoomsUseCaseImpl(
    private val repository: RoomTransferRepository
) : GetRoomsUseCase {
    override suspend fun invoke(): Flow<List<Room>> {
        return repository.getRooms()
    }
}
