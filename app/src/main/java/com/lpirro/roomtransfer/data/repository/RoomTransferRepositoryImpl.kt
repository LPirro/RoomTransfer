package com.lpirro.roomtransfer.data.repository

import com.bumptech.glide.load.HttpException
import com.lpirro.roomtransfer.data.network.RoomTransferService
import com.lpirro.roomtransfer.data.persistance.RoomsDao
import com.lpirro.roomtransfer.data.repository.mapper.LocalRoomMapper
import com.lpirro.roomtransfer.data.repository.mapper.RoomMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException

class RoomTransferRepositoryImpl(
    private val roomTransferService: RoomTransferService,
    private val roomsDao: RoomsDao,
    private val localRoomMapper: LocalRoomMapper,
    private val roomMapper: RoomMapper
) : RoomTransferRepository {

    override suspend fun getRooms() = flow {
        loadRooms()
        val rooms = roomsDao.getAll().map { roomMapper.mapLocalToRoom(it) }
        emit(rooms)
    }.flowOn(Dispatchers.IO)

    override suspend fun bookRoom() {
        roomTransferService.bookRoom()
    }

    private suspend fun loadRooms() {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (roomsDao.getAll().isEmpty())
                            throw Exception()
                    }
                    else -> throw e
                }
            }
        }
    }

    private suspend fun refreshCache() {
        val remoteRooms = roomTransferService.fetchRooms().rooms
        roomsDao.deleteAll()
        roomsDao.insertAll(
            remoteRooms.map {
                localRoomMapper.mapRemoteToLocalRoom(it)
            }
        )
    }
}
