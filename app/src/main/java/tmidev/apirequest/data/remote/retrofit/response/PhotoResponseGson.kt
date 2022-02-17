package tmidev.apirequest.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import tmidev.apirequest.domain.model.Photo

data class PhotoResponseGson(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
) {
    fun toPhoto() = Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url
    )
}