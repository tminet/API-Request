package tmidev.apirequest.core.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchers
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchersImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindsCoroutinesDispatchers(
        dispatchers: CoroutinesDispatchersImpl
    ): CoroutinesDispatchers
}