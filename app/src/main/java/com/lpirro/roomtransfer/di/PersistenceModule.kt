package com.lpirro.roomtransfer.di

import android.app.Application
import androidx.room.Room
import com.lpirro.roomtransfer.data.persistance.RoomTransferDatabase
import com.lpirro.roomtransfer.data.persistance.RoomsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
    ): RoomTransferDatabase {
        return Room
            .databaseBuilder(application, RoomTransferDatabase::class.java, "roomtransfer.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(roomTransferDatabase: RoomTransferDatabase): RoomsDao {
        return roomTransferDatabase.roomsDao()
    }
}
