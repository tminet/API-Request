package tmidev.apirequest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import tmidev.apirequest.util.ImageLoader
import tmidev.apirequest.util.ImageLoaderImplCoil

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule {
    @Binds
    fun bindImageLoader(
        imageLoader: ImageLoaderImplCoil
    ): ImageLoader
}