package com.amaizzzing.sobes4.di.modules

import com.amaizzzing.sobes4.data.services.ITimerService
import com.amaizzzing.sobes4.data.services.TimerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {
    @Singleton
    @Provides
    fun timerService(): ITimerService = TimerService()
}