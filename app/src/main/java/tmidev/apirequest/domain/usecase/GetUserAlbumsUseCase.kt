package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.type.ResultType

interface GetUserAlbumsUseCase {
    operator fun invoke(userId: Int): Flow<ResultType<List<Album>>>
}