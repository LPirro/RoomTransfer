package com.lpirro.roomtransfer.data.repository.mapper

import com.lpirro.roomtransfer.data.network.model.RemoteRoom
import com.lpirro.roomtransfer.data.persistance.model.LocalRoom

interface LocalRoomMapper {
    fun mapRemoteToLocalRoom(remoteRoom: RemoteRoom): LocalRoom
}
