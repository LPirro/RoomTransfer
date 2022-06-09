package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.data.network.model.RemoteRoom
import com.lpirro.roomtransfer.data.persistance.model.LocalRoom

class LocalRoomMapperImpl : LocalRoomMapper {
    override fun mapRemoteToLocalRoom(remoteRoom: RemoteRoom) = LocalRoom(
        name = remoteRoom.name,
        spots = remoteRoom.spots,
        thumbnail = remoteRoom.thumbnail
    )
}
