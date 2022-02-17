package tmidev.apirequest.data.remote

import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.model.User

interface DataSourceRemote {
    suspend fun getUsers(): List<User>

    suspend fun getUserPosts(userId: Int): List<Post>

    suspend fun getUserAlbums(userId: Int): List<Album>

    suspend fun getAlbumPhotos(albumId: Int): List<Photo>
}