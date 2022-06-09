package com.lpirro.roomtransfer.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lpirro.roomtransfer.data.persistance.model.LocalRoom

@Database(entities = [LocalRoom::class], version = 1, exportSchema = true)
abstract class RoomTransferDatabase : RoomDatabase() {
    abstract fun roomsDao(): RoomsDao
}
