package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.data.persistance.model.LocalRoom
import com.lpirro.roomtransfer.domain.model.Room

interface RoomMapper {
    fun mapLocalToRoom(localRoom: LocalRoom): Room
}
