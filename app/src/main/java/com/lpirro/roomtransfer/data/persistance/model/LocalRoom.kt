package com.lpirro.roomtransfer.data.persistance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms_table")
data class LocalRoom(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "spots") val spots: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
)
