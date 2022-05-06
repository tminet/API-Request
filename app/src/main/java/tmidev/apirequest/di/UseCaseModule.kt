package tmidev.apirequest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tmidev.apirequest.domain.usecase.GetAlbumPhotosUseCase
import tmidev.apirequest.domain.usecase.GetAlbumPhotosUseCaseImpl
import tmidev.apirequest.domain.usecase.GetUserAlbumsUseCase
import tmidev.apirequest.domain.usecase.GetUserAlbumsUseCaseImpl
import tmidev.apirequest.domain.usecase.GetUserPostsUseCase
import tmidev.apirequest.domain.usecase.GetUserPostsUseCaseImpl
import tmidev.apirequest.domain.usecase.GetUsersUseCase
import tmidev.apirequest.domain.usecase.GetUsersUseCaseImpl

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