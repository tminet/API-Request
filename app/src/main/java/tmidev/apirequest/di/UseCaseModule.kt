package tmidev.apirequest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tmidev.apirequest.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetUsers(
        useCase: GetUsersUseCaseImpl
    ): GetUsersUseCase

    @Binds
    fun bindGetUserPosts(
        useCase: GetUserPostsUseCaseImpl
    ): GetUserPostsUseCase

    @Binds
    fun bindGetUserAlbums(
        useCase: GetUserAlbumsUseCaseImpl
    ): GetUserAlbumsUseCase

    @Binds
    fun bindGetAlbumPhotos(
        useCase: GetAlbumPhotosUseCaseImpl
    ): GetAlbumPhotosUseCase
}