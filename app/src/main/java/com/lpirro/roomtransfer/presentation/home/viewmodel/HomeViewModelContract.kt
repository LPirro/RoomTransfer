package com.lpirro.roomtransfer.presentation.home.viewmodel

import kotlinx.coroutines.Job

interface HomeViewModelContract {
    fun getRooms(): Job
    fun bookRoom(): Job
}
