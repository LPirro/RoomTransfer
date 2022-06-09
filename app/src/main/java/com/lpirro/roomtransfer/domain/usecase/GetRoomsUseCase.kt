package com.lpirro.roomtransfer.domain.usecase

import com.lpirro.roomtransfer.domain.model.Room
import kotlinx.coroutines.flow.Flow

interface GetRoomsUseCase {
    suspend operator fun invoke(): Flow<List<Room>>
}
