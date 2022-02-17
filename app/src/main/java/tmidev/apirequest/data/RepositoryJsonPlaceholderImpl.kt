package tmidev.apirequest.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tmidev.apirequest.data.remote.DataSourceRemote
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.domain.type.ResultType
import javax.inject.Inject

class RepositoryJsonPlaceholderImpl @Inject constructor(
    private val dataSourceRemote: DataSourceRemote
) : RepositoryJsonPlaceholder {
    override fun getUsers(): Flow<ResultType<List<User>>> = flow {
        emit(value = ResultType.Loading)
        try {
            val result = dataSourceRemote.getUsers()
            emit(value = ResultType.Success(data = result))
        } catch (error: Exception) {
            error.printStackTrace()
            emit(value = ResultType.Error)
        }
    }

    override fun getUserPosts(userId: Int): Flow<ResultType<List<Post>>> = flow {
        emit(value = ResultType.Loading)
        try {
            val result = dataSourceRemote.getUserPosts(userId = userId)
            emit(value = ResultType.Success(data = result))
        } catch (error: Exception) {
            error.printStackTrace()
            emit(value = ResultType.Error)
        }
    }

    override fun getUserAlbums(userId: Int): Flow<ResultType<List<Album>>> = flow {
        emit(value = ResultType.Loading)
        try {
            val result = dataSourceRemote.getUserAlbums(userId = userId)
            emit(value = ResultType.Success(data = result))
        } catch (error: Exception) {
            error.printStackTrace()
            emit(value = ResultType.Error)
        }
    }

    override fun getAlbumPhotos(albumId: Int): Flow<ResultType<List<Photo>>> = flow {
        emit(value = ResultType.Loading)
        try {
            val result = dataSourceRemote.getAlbumPhotos(albumId = albumId)
            emit(value = ResultType.Success(data = result))
        } catch (error: Exception) {
            error.printStackTrace()
            emit(value = ResultType.Error)
        }
    }
}