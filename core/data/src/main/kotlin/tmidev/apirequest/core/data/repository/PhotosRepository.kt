package tmidev.apirequest.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchers
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.asErrorType
import tmidev.apirequest.core.model.Photo
import tmidev.apirequest.core.network.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.core.network.retrofit.JsonPlaceholderApiRetrofit
import javax.inject.Inject
import tmidev.apirequest.core.network.ktor.response.PhotoResponse as PhotoResponseKt
import tmidev.apirequest.core.network.retrofit.response.PhotoResponse as PhotoResponseGs

interface PhotosRepository {
    fun getPhotosByAlbumId(
        albumId: Int
    ): Flow<ResultType<List<Photo>>>
}

class PhotosRepositoryImplKtor @Inject constructor(
    private val jsonPlaceholderApiKtor: JsonPlaceholderApiKtor,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : PhotosRepository {
    override fun getPhotosByAlbumId(
        albumId: Int
    ): Flow<ResultType<List<Photo>>> = flow<ResultType<List<Photo>>> {
        val photos = jsonPlaceholderApiKtor.getPhotosByAlbumId(
            albumId = albumId
        ).map { it.asPhoto() }
        emit(value = ResultType.Success(data = photos))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun PhotoResponseKt.asPhoto() = Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url
    )
}

class PhotosRepositoryImplRetrofit @Inject constructor(
    private val jsonPlaceholderApiRetrofit: JsonPlaceholderApiRetrofit,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : PhotosRepository {
    override fun getPhotosByAlbumId(
        albumId: Int
    ): Flow<ResultType<List<Photo>>> = flow<ResultType<List<Photo>>> {
        val photos = jsonPlaceholderApiRetrofit.getPhotosByAlbumId(
            albumId = albumId
        ).map { it.asPhoto() }
        emit(value = ResultType.Success(data = photos))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun PhotoResponseGs.asPhoto() = Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url
    )
}