package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.data.persistance.model.LocalRoom
import com.lpirro.roomtransfer.domain.model.Room

class RoomMapperImpl : RoomMapper {
    override fun mapLocalToRoom(localRoom: LocalRoom) = Room(
        name = localRoom.name,
        spots = localRoom.spots,
        thumbnail = localRoom.thumbnail
    )
}
