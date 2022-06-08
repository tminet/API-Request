package tmidev.apirequest.data.remote.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import tmidev.apirequest.data.remote.ktor.response.AlbumResponseKt
import tmidev.apirequest.data.remote.ktor.response.PhotoResponseKt
import tmidev.apirequest.data.remote.ktor.response.PostResponseKt
import tmidev.apirequest.data.remote.ktor.response.UserResponseKt
import tmidev.apirequest.util.API_ROUTE_ALBUMS
import tmidev.apirequest.util.API_ROUTE_PHOTOS
import tmidev.apirequest.util.API_ROUTE_POSTS
import tmidev.apirequest.util.API_ROUTE_USERS
import tmidev.apirequest.util.API_URL

class JsonPlaceholderApiKtor(
    private val httpClient: HttpClient
) {
    suspend fun getUsers(): List<UserResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_USERS)
    }.body()

    suspend fun getUserPosts(userId: Int): List<PostResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_POSTS)
        parameter(key = "userId", userId)
    }.body()

    suspend fun getUserAlbums(userId: Int): List<AlbumResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_ALBUMS)
        parameter(key = "userId", userId)
    }.body()

    suspend fun getAlbumPhotos(albumId: Int): List<PhotoResponseKt> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_PHOTOS)
        parameter(key = "albumId", albumId)
    }.body()
}