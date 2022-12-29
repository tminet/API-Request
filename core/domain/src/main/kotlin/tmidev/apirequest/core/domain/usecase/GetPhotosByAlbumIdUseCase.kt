package tmidev.apirequest.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.data.di.KtorImplementation
import tmidev.apirequest.core.data.di.RetrofitImplementation
import tmidev.apirequest.core.data.repository.PhotosRepository
import tmidev.apirequest.core.model.Photo
import javax.inject.Inject

interface GetPhotosByAlbumIdUseCase {
    operator fun invoke(albumId: Int): Flow<ResultType<List<Photo>>>
}

class GetPhotosByAlbumIdUseCaseImpl @Inject constructor(
    @KtorImplementation private val photosRepository: PhotosRepository
//    @RetrofitImplementation private val photosRepository: PhotosRepository
) : GetPhotosByAlbumIdUseCase {
    override fun invoke(albumId: Int): Flow<ResultType<List<Photo>>> =
        photosRepository.getPhotosByAlbumId(albumId = albumId)
}