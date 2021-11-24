package com.amaizzzing.sobes4

import android.app.Application
import com.amaizzzing.sobes4.di.AppComponent
import com.amaizzzing.sobes4.di.DaggerAppComponent

class ClassesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    companion object {
        lateinit var instance: ClassesApp
        lateinit var appComponent: AppComponent
    }
}