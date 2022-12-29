package tmidev.apirequest.core.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tmidev.apirequest.core.network.retrofit.response.AlbumResponse
import tmidev.apirequest.core.network.retrofit.response.PhotoResponse
import tmidev.apirequest.core.network.retrofit.response.PostResponse
import tmidev.apirequest.core.network.retrofit.response.UserResponse
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_ALBUMS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_PHOTOS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_POSTS
import tmidev.apirequest.core.network.util.Constants.API_ROUTE_USERS

interface JsonPlaceholderApiRetrofit {
    @GET(API_ROUTE_USERS)
    suspend fun getUsers(): List<UserResponse>

    @GET("$API_ROUTE_USERS/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): UserResponse

    @GET(API_ROUTE_POSTS)
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int
    ): List<PostResponse>

    @GET(API_ROUTE_ALBUMS)
    suspend fun getAlbumsByUserId(
        @Query("userId") userId: Int
    ): List<AlbumResponse>

    @GET(API_ROUTE_PHOTOS)
    suspend fun getPhotosByAlbumId(
        @Query("albumId") albumId: Int
    ): List<PhotoResponse>
}