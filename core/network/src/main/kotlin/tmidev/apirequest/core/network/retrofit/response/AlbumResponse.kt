package tmidev.apirequest.core.network.retrofit.response

import com.google.gson.annotations.SerializedName

/**
 * Model class to represent an album from api.
 */
data class AlbumResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)