package com.lpirro.roomtransfer.data.network.model

import com.google.gson.annotations.SerializedName

data class RemoteRoomResponse(
    @SerializedName("rooms") val rooms: List<RemoteRoom>
)

data class RemoteRoom(
    @SerializedName("name") val name: String,
    @SerializedName("spots") val spots: Int,
    @SerializedName("thumbnail") val thumbnail: String
)
