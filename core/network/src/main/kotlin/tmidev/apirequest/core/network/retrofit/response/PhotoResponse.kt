package tmidev.apirequest.core.network.retrofit.response

import com.google.gson.annotations.SerializedName

/**
 * Model class to represent a photo from api.
 */
data class PhotoResponse(
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
)