package com.amaizzzing.sobes4.di.modules

import android.widget.ImageView
import com.amaizzzing.sobes4.data.services.GlideImageViewLoader
import com.amaizzzing.sobes4.data.services.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Provides
    @Singleton
    fun imageLoader(): IImageLoader<ImageView> =
        GlideImageViewLoader()
}