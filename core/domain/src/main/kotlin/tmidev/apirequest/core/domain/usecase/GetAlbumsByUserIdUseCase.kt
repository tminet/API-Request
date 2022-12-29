package tmidev.apirequest.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.data.di.KtorImplementation
import tmidev.apirequest.core.data.di.RetrofitImplementation
import tmidev.apirequest.core.data.repository.AlbumsRepository
import tmidev.apirequest.core.model.Album
import javax.inject.Inject

interface GetAlbumsByUserIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultType<List<Album>>>
}

class GetAlbumsByUserIdUseCaseImpl @Inject constructor(
    @KtorImplementation private val albumsRepository: AlbumsRepository
//    @RetrofitImplementation private val albumsRepository: AlbumsRepository
) : GetAlbumsByUserIdUseCase {
    override fun invoke(userId: Int): Flow<ResultType<List<Album>>> =
        albumsRepository.getAlbumsByUserId(userId = userId)
}