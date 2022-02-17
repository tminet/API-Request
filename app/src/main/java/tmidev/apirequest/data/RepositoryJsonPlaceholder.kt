package tmidev.apirequest.data

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.domain.type.ResultType

interface RepositoryJsonPlaceholder {
    fun getUsers(): Flow<ResultType<List<User>>>

    fun getUserPosts(userId: Int): Flow<ResultType<List<Post>>>

    fun getUserAlbums(userId: Int): Flow<ResultType<List<Album>>>

    fun getAlbumPhotos(albumId: Int): Flow<ResultType<List<Photo>>>
}