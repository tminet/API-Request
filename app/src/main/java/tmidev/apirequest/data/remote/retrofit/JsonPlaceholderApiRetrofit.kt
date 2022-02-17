package tmidev.apirequest.data.remote.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import tmidev.apirequest.data.remote.retrofit.response.AlbumResponseGson
import tmidev.apirequest.data.remote.retrofit.response.PhotoResponseGson
import tmidev.apirequest.data.remote.retrofit.response.PostResponseGson
import tmidev.apirequest.data.remote.retrofit.response.UserResponseGson
import tmidev.apirequest.util.API_ROUTE_ALBUMS
import tmidev.apirequest.util.API_ROUTE_PHOTOS
import tmidev.apirequest.util.API_ROUTE_POSTS
import tmidev.apirequest.util.API_ROUTE_USERS

interface JsonPlaceholderApiRetrofit {
    @GET(API_ROUTE_USERS)
    suspend fun getUsers(): List<UserResponseGson>

    @GET(API_ROUTE_POSTS)
    suspend fun getUserPosts(
        @Query("userId") userId: Int
    ): List<PostResponseGson>

    @GET(API_ROUTE_ALBUMS)
    suspend fun getUserAlbums(
        @Query("userId") userId: Int
    ): List<AlbumResponseGson>

    @GET(API_ROUTE_PHOTOS)
    suspend fun getAlbumPhotos(
        @Query("albumId") albumId: Int
    ): List<PhotoResponseGson>
}