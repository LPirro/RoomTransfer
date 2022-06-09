package com.lpirro.roomtransfer.di

import com.lpirro.roomtransfer.data.repository.RoomTransferRepository
import com.lpirro.roomtransfer.domain.usecase.BookRoomUseCase
import com.lpirro.roomtransfer.domain.usecase.BookRoomUseCaseImpl
import com.lpirro.roomtransfer.domain.usecase.GetRoomsUseCase
import com.lpirro.roomtransfer.domain.usecase.GetRoomsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesGetRoomsUseCase(repository: RoomTransferRepository): GetRoomsUseCase {
        return GetRoomsUseCaseImpl(repository)
    }

    @Provides
    fun providesBookRoomUseCase(repository: RoomTransferRepository): BookRoomUseCase {
        return BookRoomUseCaseImpl(repository)
    }
}
