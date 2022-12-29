package tmidev.apirequest.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.apirequest.core.data.repository.AlbumsRepository
import tmidev.apirequest.core.data.repository.AlbumsRepositoryImplKtor
import tmidev.apirequest.core.data.repository.AlbumsRepositoryImplRetrofit
import tmidev.apirequest.core.data.repository.PhotosRepository
import tmidev.apirequest.core.data.repository.PhotosRepositoryImplKtor
import tmidev.apirequest.core.data.repository.PhotosRepositoryImplRetrofit
import tmidev.apirequest.core.data.repository.PostsRepository
import tmidev.apirequest.core.data.repository.PostsRepositoryImplKtor
import tmidev.apirequest.core.data.repository.PostsRepositoryImplRetrofit
import tmidev.apirequest.core.data.repository.UsersRepository
import tmidev.apirequest.core.data.repository.UsersRepositoryImplKtor
import tmidev.apirequest.core.data.repository.UsersRepositoryImplRetrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    @KtorImplementation
    fun bindsUsersRepositoryKtor(
        repository: UsersRepositoryImplKtor
    ): UsersRepository

    @Binds
    @Singleton
    @RetrofitImplementation
    fun bindsUsersRepositoryRetrofit(
        repository: UsersRepositoryImplRetrofit
    ): UsersRepository

    @Binds
    @Singleton
    @KtorImplementation
    fun bindsPostsRepositoryKtor(
        repository: PostsRepositoryImplKtor
    ): PostsRepository

    @Binds
    @Singleton
    @RetrofitImplementation
    fun bindsPostsRepositoryRetrofit(
        repository: PostsRepositoryImplRetrofit
    ): PostsRepository

    @Binds
    @Singleton
    @KtorImplementation
    fun bindsAlbumsRepositoryKtor(
        repository: AlbumsRepositoryImplKtor
    ): AlbumsRepository

    @Binds
    @Singleton
    @RetrofitImplementation
    fun bindsAlbumsRepositoryRetrofit(
        repository: AlbumsRepositoryImplRetrofit
    ): AlbumsRepository

    @Binds
    @Singleton
    @KtorImplementation
    fun bindsPhotosRepositoryKtor(
        repository: PhotosRepositoryImplKtor
    ): PhotosRepository

    @Binds
    @Singleton
    @RetrofitImplementation
    fun bindsPhotosRepositoryRetrofit(
        repository: PhotosRepositoryImplRetrofit
    ): PhotosRepository
}