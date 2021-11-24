package com.amaizzzing.sobes4.di.modules

import androidx.lifecycle.ViewModel
import com.amaizzzing.sobes4.di.ViewModelKey
import com.amaizzzing.sobes4.ui.viewmodels.MainViewModel
import com.amaizzzing.sobes4.ui.viewmodels.ScheduleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel
}
