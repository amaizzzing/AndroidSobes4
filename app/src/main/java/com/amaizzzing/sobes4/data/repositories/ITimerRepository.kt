package com.amaizzzing.sobes4.data.repositories

import kotlinx.coroutines.flow.Flow

interface ITimerRepository {
    fun startTimer(newTime: Long): Flow<Long>?

    fun getCurrentTime(): Long
}