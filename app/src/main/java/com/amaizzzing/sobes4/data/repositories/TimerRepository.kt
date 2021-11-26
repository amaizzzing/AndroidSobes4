package com.amaizzzing.sobes4.data.repositories

import com.amaizzzing.sobes4.data.services.ITimerService
import kotlinx.coroutines.flow.Flow

class TimerRepository(private val timer: ITimerService): ITimerRepository {
    override fun startTimer(newTime: Long): Flow<Long>? {
        return timer.startTimer(newTime)
    }

    override fun getCurrentTime(): Long = timer.getCurrentTimerTime()
}