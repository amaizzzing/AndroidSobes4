package com.amaizzzing.sobes4.di

import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.di.modules.ImageModule
import com.amaizzzing.sobes4.di.modules.RepositoryModule
import com.amaizzzing.sobes4.di.modules.ServiceModule
import com.amaizzzing.sobes4.di.modules.ViewModelModule
import com.amaizzzing.sobes4.ui.fragments.MainFragment
import com.amaizzzing.sobes4.ui.fragments.ScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelModule::class,
    RepositoryModule::class,
    ImageModule::class,
    ServiceModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ClassesApp): Builder

        fun build(): AppComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(scheduleFragment: ScheduleFragment)
}