package tmidev.apirequest.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchers
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.asErrorType
import tmidev.apirequest.core.model.Album
import tmidev.apirequest.core.network.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.core.network.retrofit.JsonPlaceholderApiRetrofit
import javax.inject.Inject
import tmidev.apirequest.core.network.ktor.response.AlbumResponse as AlbumResponseKt
import tmidev.apirequest.core.network.retrofit.response.AlbumResponse as AlbumResponseGs

interface AlbumsRepository {
    fun getAlbumsByUserId(
        userId: Int
    ): Flow<ResultType<List<Album>>>
}

class AlbumsRepositoryImplKtor @Inject constructor(
    private val jsonPlaceholderApiKtor: JsonPlaceholderApiKtor,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : AlbumsRepository {
    override fun getAlbumsByUserId(
        userId: Int
    ): Flow<ResultType<List<Album>>> = flow<ResultType<List<Album>>> {
        val albums = jsonPlaceholderApiKtor.getAlbumsByUserId(
            userId = userId
        ).map { it.asAlbum() }
        emit(value = ResultType.Success(data = albums))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun AlbumResponseKt.asAlbum() = Album(
        userId = userId,
        id = id,
        title = title
    )
}

class AlbumsRepositoryImplRetrofit @Inject constructor(
    private val jsonPlaceholderApiRetrofit: JsonPlaceholderApiRetrofit,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : AlbumsRepository {
    override fun getAlbumsByUserId(
        userId: Int
    ): Flow<ResultType<List<Album>>> = flow<ResultType<List<Album>>> {
        val albums = jsonPlaceholderApiRetrofit.getAlbumsByUserId(
            userId = userId
        ).map { it.asAlbum() }
        emit(value = ResultType.Success(data = albums))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun AlbumResponseGs.asAlbum() = Album(
        userId = userId,
        id = id,
        title = title
    )
}