package com.lpirro.roomtransfer.di

import com.lpirro.roomtransfer.data.network.RoomTransferService
import com.lpirro.roomtransfer.data.persistance.RoomsDao
import com.lpirro.roomtransfer.data.repository.RoomTransferRepository
import com.lpirro.roomtransfer.data.repository.RoomTransferRepositoryImpl
import com.lpirro.roomtransfer.data.repository.mapper.LocalRoomMapper
import com.lpirro.roomtransfer.data.repository.mapper.LocalRoomMapperImpl
import com.lpirro.roomtransfer.data.repository.mapper.RoomMapper
import com.lpirro.roomtransfer.data.repository.mapper.RoomMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRoomTransferRepository(
        roomTransferService: RoomTransferService,
        roomsDao: RoomsDao,
        localRoomMapper: LocalRoomMapper,
        roomMapper: RoomMapper
    ): RoomTransferRepository {
        return RoomTransferRepositoryImpl(
            roomTransferService,
            roomsDao,
            localRoomMapper,
            roomMapper
        )
    }

    @Provides
    fun provideLocalRoomMapper(): LocalRoomMapper {
        return LocalRoomMapperImpl()
    }

    @Provides
    fun provideRoomMapper(): RoomMapper {
        return RoomMapperImpl()
    }
}
