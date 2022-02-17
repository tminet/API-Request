package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.data.RepositoryJsonPlaceholder
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.type.ResultType
import javax.inject.Inject

class GetUserAlbumsUseCaseImpl @Inject constructor(
    private val repository: RepositoryJsonPlaceholder
) : GetUserAlbumsUseCase {
    override fun invoke(userId: Int): Flow<ResultType<List<Album>>> =
        repository.getUserAlbums(userId = userId)
}