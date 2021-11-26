package com.amaizzzing.sobes4.data.services

import kotlinx.coroutines.flow.Flow

interface ITimerService {
    var timerFlow: Flow<Long>?

    fun startTimer(newTime: Long): Flow<Long>?

    fun getCurrentTimerTime(): Long
}