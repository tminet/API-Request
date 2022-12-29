package tmidev.apirequest.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tmidev.apirequest.core.domain.usecase.GetAlbumsByUserIdUseCase
import tmidev.apirequest.core.domain.usecase.GetAlbumsByUserIdUseCaseImpl
import tmidev.apirequest.core.domain.usecase.GetPhotosByAlbumIdUseCase
import tmidev.apirequest.core.domain.usecase.GetPhotosByAlbumIdUseCaseImpl
import tmidev.apirequest.core.domain.usecase.GetPostsByUserIdUseCase
import tmidev.apirequest.core.domain.usecase.GetPostsByUserIdUseCaseImpl
import tmidev.apirequest.core.domain.usecase.GetUserByIdUseCase
import tmidev.apirequest.core.domain.usecase.GetUserByIdUseCaseImpl
import tmidev.apirequest.core.domain.usecase.GetUsersUseCase
import tmidev.apirequest.core.domain.usecase.GetUsersUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    @ViewModelScoped
    fun bindsGetUsersUseCase(
        useCase: GetUsersUseCaseImpl
    ): GetUsersUseCase

    @Binds
    @ViewModelScoped
    fun bindsGetUserByIdUseCase(
        useCase: GetUserByIdUseCaseImpl
    ): GetUserByIdUseCase

    @Binds
    @ViewModelScoped
    fun bindsGetPostsByUserIdUseCase(
        useCase: GetPostsByUserIdUseCaseImpl
    ): GetPostsByUserIdUseCase

    @Binds
    @ViewModelScoped
    fun bindsGetAlbumsByUserIdUseCase(
        useCase: GetAlbumsByUserIdUseCaseImpl
    ): GetAlbumsByUserIdUseCase

    @Binds
    @ViewModelScoped
    fun bindsGetPhotosByAlbumIdUseCase(
        useCase: GetPhotosByAlbumIdUseCaseImpl
    ): GetPhotosByAlbumIdUseCase
}