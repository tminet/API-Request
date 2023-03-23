package tmidev.apirequest.core.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import tmidev.apirequest.core.network.ktor.response.AlbumResponse
import tmidev.apirequest.core.network.ktor.response.PhotoResponse
import tmidev.apirequest.core.network.ktor.response.PostResponse
import tmidev.apirequest.core.network.ktor.response.UserResponse
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_ALBUMS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_PHOTOS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_POSTS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_USERS
import tmidev.apirequest.core.network.util.Constants.API_URL

class JsonPlaceholderApiKtor(
    private val httpClient: HttpClient
) {
    suspend fun getUsers(): List<UserResponse> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_USERS)
    }.body()

    suspend fun getUserById(userId: Int): UserResponse = httpClient.get {
        url(urlString = "$API_URL$API_ROUTE_USERS/$userId")
    }.body()

    suspend fun getPostsByUserId(
        userId: Int
    ): List<PostResponse> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_POSTS)
        parameter(key = "userId", value = userId)
    }.body()

    suspend fun getAlbumsByUserId(
        userId: Int
    ): List<AlbumResponse> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_ALBUMS)
        parameter(key = "userId", value = userId)
    }.body()

    suspend fun getPhotosByAlbumId(
        albumId: Int
    ): List<PhotoResponse> = httpClient.get {
        url(urlString = API_URL + API_ROUTE_PHOTOS)
        parameter(key = "albumId", value = albumId)
    }.body()
}