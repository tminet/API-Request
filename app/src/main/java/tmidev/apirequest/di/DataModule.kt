package tmidev.apirequest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.apirequest.data.RepositoryJsonPlaceholder
import tmidev.apirequest.data.RepositoryJsonPlaceholderImpl
import tmidev.apirequest.data.remote.DataSourceRemote
import tmidev.apirequest.data.remote.DataSourceRemoteImplKtor
import tmidev.apirequest.data.remote.DataSourceRemoteImplRetrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    @RetrofitImplementation
    fun bindDataSourceRemoteRetrofit(
        dataSource: DataSourceRemoteImplRetrofit
    ): DataSourceRemote

    @Binds
    @Singleton
    @KtorImplementation
    fun bindDataSourceRemoteKtor(
        dataSource: DataSourceRemoteImplKtor
    ): DataSourceRemote

    @Binds
    @Singleton
    fun bindRepositoryJsonPlaceholder(
        repository: RepositoryJsonPlaceholderImpl
    ): RepositoryJsonPlaceholder
}