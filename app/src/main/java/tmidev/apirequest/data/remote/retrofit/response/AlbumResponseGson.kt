package tmidev.apirequest.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import tmidev.apirequest.domain.model.Album

data class AlbumResponseGson(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
) {
    fun toAlbum() = Album(
        userId = userId,
        id = id,
        title = title
    )
}