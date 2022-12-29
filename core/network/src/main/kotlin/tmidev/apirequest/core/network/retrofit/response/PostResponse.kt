package tmidev.apirequest.core.network.retrofit.response

import com.google.gson.annotations.SerializedName

/**
 * Model class to represent a post from api.
 */
data class PostResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)