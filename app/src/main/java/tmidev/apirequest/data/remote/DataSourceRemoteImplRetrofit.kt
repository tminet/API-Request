package tmidev.apirequest.data.remote

import tmidev.apirequest.data.remote.retrofit.JsonPlaceholderApiRetrofit
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.model.User
import javax.inject.Inject

class DataSourceRemoteImplRetrofit @Inject constructor(
    private val jsonPlaceholderApi: JsonPlaceholderApiRetrofit
) : DataSourceRemote {
    override suspend fun getUsers(): List<User> =
        jsonPlaceholderApi.getUsers().map { userResponse ->
            userResponse.toUser()
        }

    override suspend fun getUserPosts(userId: Int): List<Post> =
        jsonPlaceholderApi.getUserPosts(userId = userId).map { postResponse ->
            postResponse.toPost()
        }

    override suspend fun getUserAlbums(userId: Int): List<Album> =
        jsonPlaceholderApi.getUserAlbums(userId = userId).map { albumResponse ->
            albumResponse.toAlbum()
        }

    override suspend fun getAlbumPhotos(albumId: Int): List<Photo> =
        jsonPlaceholderApi.getAlbumPhotos(albumId = albumId).map { photoResponse ->
            photoResponse.toPhoto()
        }
}