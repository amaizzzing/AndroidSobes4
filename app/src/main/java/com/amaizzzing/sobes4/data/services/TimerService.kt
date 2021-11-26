package com.amaizzzing.sobes4.data.services

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TimerService(): ITimerService {
    override var timerFlow: Flow<Long>? = null
    private var currentTimerTime: Long = 0

    override fun startTimer(newTime: Long): Flow<Long>? {
        timerFlow =
            (0..(newTime))
                .asFlow()
                .map { newTime - it }
                .onEach {
                    currentTimerTime = it
                    delay(1000)
                }
        return timerFlow
    }

    override fun getCurrentTimerTime() = currentTimerTime
}