package com.lpirro.roomtransfer.domain.usecase

import com.lpirro.roomtransfer.data.repository.RoomTransferRepository

class BookRoomUseCaseImpl(
    private val repository: RoomTransferRepository
) : BookRoomUseCase {
    override suspend fun invoke() {
        return repository.bookRoom()
    }
}
