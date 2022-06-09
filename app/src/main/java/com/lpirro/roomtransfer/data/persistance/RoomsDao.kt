package com.lpirro.roomtransfer.data.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lpirro.roomtransfer.data.persistance.model.LocalRoom

@Dao
interface RoomsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rooms: List<LocalRoom>)

    @Query("SELECT * FROM rooms_table")
    fun getAll(): List<LocalRoom>

    @Query("DELETE FROM rooms_table")
    fun deleteAll()
}
