package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.type.ResultType

interface GetAlbumPhotosUseCase {
    operator fun invoke(albumId: Int): Flow<ResultType<List<Photo>>>
}