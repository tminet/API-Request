package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.data.RepositoryJsonPlaceholder
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.type.ResultType
import javax.inject.Inject

class GetAlbumPhotosUseCaseImpl @Inject constructor(
    private val repository: RepositoryJsonPlaceholder
) : GetAlbumPhotosUseCase {
    override fun invoke(albumId: Int): Flow<ResultType<List<Photo>>> =
        repository.getAlbumPhotos(albumId = albumId)
}