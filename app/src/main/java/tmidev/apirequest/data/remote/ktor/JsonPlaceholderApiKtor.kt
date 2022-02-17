package tmidev.apirequest.data.remote.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import tmidev.apirequest.data.remote.ktor.response.AlbumResponseKt
import tmidev.apirequest.data.remote.ktor.response.PhotoResponseKt
import tmidev.apirequest.data.remote.ktor.response.PostResponseKt
import tmidev.apirequest.data.remote.ktor.response.UserResponseKt
import tmidev.apirequest.util.*
import javax.inject.Inject

class JsonPlaceholderApiKtor @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getUsers(): List<UserResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_USERS)
    }

    suspend fun getUserPosts(userId: Int): List<PostResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_POSTS)
        parameter("userId", userId)
    }

    suspend fun getUserAlbums(userId: Int): List<AlbumResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_ALBUMS)
        parameter("userId", userId)
    }

    suspend fun getAlbumPhotos(albumId: Int): List<PhotoResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_PHOTOS)
        parameter("albumId", albumId)
    }
}